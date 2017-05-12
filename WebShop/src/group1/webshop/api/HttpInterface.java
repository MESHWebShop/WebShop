package group1.webshop.api;

import java.util.Map;

import group1.webshop.api.validators.JsonMapValidator;

/**
 * HTTP Interface class
 * 
 * @author Emil Bertilsson
 */
public class HttpInterface {

    /**
     * Validates a JSON data map using a map validator
     * 
     * @param validator Map validator
     * @param jsonMap JSON data map
     * @return Errors in JSON format
     */
    public Map<String, String> validateJsonMap(JsonMapValidator validator, Map<String, Object> jsonMap) {
        return validator.validate(jsonMap);
    }

}
