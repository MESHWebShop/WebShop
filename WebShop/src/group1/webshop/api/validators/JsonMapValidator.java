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
     * @param key
     * @return JSON error text
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
     * @return
     */
    @SuppressWarnings({ "unchecked", "serial" })
    private Map<String, Object> validate(Map<String, Object> validationMap, Map<String, Object> jsonMap) {
        final Map<String, Object> errMap = new HashMap<>();

        validationMap.forEach((k, v) -> {
            if (!jsonMap.containsKey(k)
                    && JsonMapValidatorEntry.class.isInstance(v)
                    && ((JsonMapValidatorEntry) v).isRequired()) {
                // Tests if the map contains the required key
                errMap.put(k, new HashMap<String, Object>() {
                    {
                        put("ERR_MISSINGKEY", "Key is missing");
                    }
                });
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
                    errMap.put(k, new HashMap<String, Object>() {
                        {
                            put("ERR_NONMAP", "Not a map object");
                        }
                    });
                }
            } else {
                try {
                    putIfNotEmpty(errMap, k, ((JsonMapValidatorEntry) v)
                            .test(jsonMap.get(k)));
                } catch (ClassCastException e) {
                    errMap.put("ERR_SERVER", "Server side error");
                }
            }
        });

        return errMap;
    }

}
