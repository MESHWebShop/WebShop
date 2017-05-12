package group1.webshop.api.validators;

import java.util.Map;
import java.util.HashMap;

import group1.webshop.api.JsonInterface;

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
     * Validates the JSON data map to this validator
     * 
     * @param jsonMap JSON data map
     * @return JSON error text
     */
    public Map<String, String> validate(Map<String, Object> jsonMap) {
        final Map<String, Object> validationMap = this.getValidationMap();
        final Map<String, String> errMap = new HashMap<>();

        // TODO: Implement map testing

        return errMap;
    }

}
