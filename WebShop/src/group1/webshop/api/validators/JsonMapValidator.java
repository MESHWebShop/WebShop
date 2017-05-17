package group1.webshop.api.validators;

import java.util.Map;
import java.util.HashMap;

/**
 * JSON Map validator class
 * 
 * @author Emil Bertilsson
 */
public abstract class JsonMapValidator {

    /**
     * Gets the validation map for this validator
     * 
     * @return Validation map
     */
    protected abstract Map<String, Object> getValidationMap();

    /**
     * Puts the key in the source map if the content map is non-empty
     * 
     * @param sourceMap Source map
     * @param key Key
     * @param contentMap Content map
     */
    private void putIfNotEmpty(Map<String, Object> sourceMap, String key, Map<String, Object> contentMap) {
        if (!contentMap.isEmpty()) {
            sourceMap.put(key, contentMap);
        }
    }

    /**
     * Validates the JSON data map to this validator
     * 
     * @param jsonMap JSON data map
     * @param key Key
     * @return Error map
     */
    public Map<String, Object> validate(Map<String, Object> jsonMap) {
        return validate(getValidationMap(), jsonMap);
    }

    /**
     * Recursively validates the JSON map
     * 
     * @param validationMap Validation map
     * @param jsonMap JSON map
     * @param key Key
     * @return Error map
     */
    @SuppressWarnings({ "unchecked" })
    private Map<String, Object> validate(Map<String, Object> validationMap, Map<String, Object> jsonMap) {
        final Map<String, Object> errMap = new HashMap<>();

        validationMap.forEach((k, v) -> {
            if (!jsonMap.containsKey(k)
                    && JsonMapValidatorEntry.class.isInstance(v)
                    && ((JsonMapValidatorEntry) v).isRequired()) {
                // Tests if the map contains the required key
                final Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("ERR_MISSINGKEY", "Key is missing");

                errMap.put(k, tempMap);
            } else if (Map.class.isInstance(validationMap.get(k))) {
                // Tests if the validation entry is a map
                if (Map.class.isInstance(jsonMap.get(k))) {
                    // Tests if the map entry is a map
                    try {
                        putIfNotEmpty(errMap, k, validate(
                                (Map<String, Object>) v,
                                (Map<String, Object>) jsonMap.get(k)));
                    } catch (ClassCastException e) {
                        errMap.put("ERR_MALFORMEDJSON", "JSON is malformed at the server-side");
                    }
                } else {
                    // Otherwise add an error
                    final Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("ERR_NONMAP", "Not a map object");

                    errMap.put(k, tempMap);
                }
            } else {
                try {
                    Map<String, Object> result = ((JsonMapValidatorEntry) v)
                            .test(jsonMap.get(k));
                    putIfNotEmpty(errMap, k, result);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    errMap.put("ERR_SERVER", "Server side error");
                }
            }
        });

        return errMap;
    }

}
