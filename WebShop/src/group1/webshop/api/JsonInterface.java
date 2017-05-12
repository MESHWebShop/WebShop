package group1.webshop.api;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * JSON Interface class
 * 
 * @author Emil Bertilsson
 */
public class JsonInterface {

    /**
     * GSON Serializer object
     */
    private static final Gson GSON_OBJ = new GsonBuilder().serializeNulls().create();

    /**
     * Converts a java Object to JSON text
     * 
     * @param obj Object to convert
     * @return JSON text
     */
    public static String toJson(Object obj) {
        return GSON_OBJ.toJson(obj);
    }

    /**
     * Parses a JSON text to a data map
     * 
     * @param json JSON text
     * @return Data map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(String json)
            throws JsonSyntaxException {
        return (Map<String, Object>) GSON_OBJ.fromJson(json, Map.class);
    }

}
