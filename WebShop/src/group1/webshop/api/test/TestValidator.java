package group1.webshop.api.test;

import java.util.HashMap;
import java.util.Map;

import group1.webshop.api.validators.JsonMapValidatorEntry;
import group1.webshop.api.validators.JsonMapValidator;

/**
 * Test validator
 * 
 * @author Emil Bertilsson
 */
public class TestValidator extends JsonMapValidator {

    /**
     * Validation map
     */
    @SuppressWarnings("serial")
    private static final Map<String, Object> validationMap = new HashMap<String, Object>() {
        {
            put("number1", new JsonMapValidatorEntry(Double.class, true)
                    .range(20.0d, 100.0d));
            put("number2", new JsonMapValidatorEntry(Double.class, true)
                    .min(100.0d));
            put("number3", new JsonMapValidatorEntry(Double.class, true)
                    .max(5.0d));
            put("map1", new HashMap<String, Object>() {
                {
                    put("string1", new JsonMapValidatorEntry(String.class, true)
                            .length(5));
                    put("string2", new JsonMapValidatorEntry(String.class, true)
                            .minLength(5));
                    put("string3", new JsonMapValidatorEntry(String.class, true)
                            .maxLength(2));
                    put("string4", new JsonMapValidatorEntry(String.class, true)
                            .lengthRange(7, 9));
                }
            });

            put("map2", new HashMap<String, Object>());
        }
    };

    /**
     * @see group1.webshop.api.validators.JsonMapValidator#getValidationMap()
     */
    @Override
    protected Map<String, Object> getValidationMap() {
        return validationMap;
    }

}
