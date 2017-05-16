package group1.webshop.api.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AccountRegistrationValidator extends JsonMapValidator {

    /**
     * Validation map
     */
    @SuppressWarnings("serial")
    private static final Map<String, Object> validationMap = new HashMap<String, Object>() {
        {
            put("username", new JsonMapValidatorEntry(String.class, true)
                    .lengthRange(6, 60));
            put("email", new JsonMapValidatorEntry(String.class, true)
                    .maxLength(60)
                    // RFC 5322 Official Standard
                    .matches(Pattern.compile(
                            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")));
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
