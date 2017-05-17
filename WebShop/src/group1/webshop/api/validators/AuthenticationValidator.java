package group1.webshop.api.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Authentication validation map
 * 
 * @author Emil Bertilsson
 */
public class AuthenticationValidator extends JsonMapValidator {

    /**
     * Validation map
     */
    @SuppressWarnings("serial")
    private static final Map<String, Object> validationMap = new HashMap<String, Object>() {
        {
            put("authentication", new JsonMapValidatorEntry(String.class, true)
                    .lengthRange(6, 60));
            put("password", new JsonMapValidatorEntry(String.class, true)
                    .lengthRange(6, 60));
        }
    };

    /**
     * @see group1.webshop.api.validators.JsonMapValidator#getValidationMap()
     */
    @Override
    protected Map<String, Object> getValidationMap() {
        // TODO Auto-generated method stub
        return validationMap;
    }

}
