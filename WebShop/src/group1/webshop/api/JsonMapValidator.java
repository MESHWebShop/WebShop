package group1.webshop.api;

import java.util.Map;
import java.util.HashMap;

import group1.webshop.api.JsonInterface;

/**
 * JSON Map validator class
 * @author Emil Bertilsson
 */
public abstract class JsonMapValidator {

    /**
     * Validates the JSON data map to this validator
     * @param jsonMap JSON data map
     * @return JSON error text
     */
    public String validate(Map<String, Object> jsonMap) {
	return JsonInterface.toJson(new HashMap<String, Object>() {{
	    put("errors", new String[] {
		    "ERR_EXAMPLE"
		});
	}});
    }

}
