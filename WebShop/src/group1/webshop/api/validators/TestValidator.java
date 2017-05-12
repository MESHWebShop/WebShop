package group1.webshop.api.validators;

import java.util.HashMap;
import java.util.Map;

/**
 * Test validator
 * 
 * @author Emil Bertilsson
 */
public class TestValidator extends JsonMapValidator {

    /**
     * Validation map
     */
    private static final Map<String, Object> validationMap = new HashMap<String, Object>() {

        private static final long serialVersionUID = 1L;
        {
            put("test", new JsonMapEntryTester("test")
                    .type(Float.class)
                    .range(10.0f, 100.0f));
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
