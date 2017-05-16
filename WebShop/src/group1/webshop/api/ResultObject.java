package group1.webshop.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Response payload class
 * 
 * @author Emil Bertilsson
 */
public class ResultObject {

    /**
     * Response error
     */
    private Map<String, Object> err = new HashMap<>();

    /**
     * Response content
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * Returns a simple result object with a single error specified
     * 
     * @param key Error key
     * @param value Error value
     * @return Simple result object
     */
    public static ResultObject simpleWithError(String key, Object value) {
        ResultObject obj = new ResultObject();
        obj.putError(key, value);
        return obj;
    }

    /**
     * Returns a simple result object with a single data entry specified
     * 
     * @param key Data key
     * @param value Data value
     * @return Simple result object
     */
    public static ResultObject simpleWithData(String key, Object value) {
        ResultObject obj = new ResultObject();
        obj.putData(key, value);
        return obj;
    }

    /**
     * Returns a simple result object with a specified data map
     * 
     * @param err Error map
     * @return Simple result object
     */
    public static ResultObject simpleWithErrorMap(Map<String, Object> err) {
        ResultObject obj = new ResultObject();
        obj.addErrors(err);
        return obj;
    }

    /**
     * Returns a simple result object with a specified data map
     * 
     * @param data Data map
     * @return Simple result object
     */
    public static ResultObject simpleWithdataMap(Map<String, Object> data) {
        ResultObject obj = new ResultObject();
        obj.addData(data);
        return obj;
    }

    /**
     * Gets the response error or null if there is no error
     * 
     * @return Response error
     */
    public Map<String, Object> getErrors() {
        return err;
    }

    /**
     * Returns true if this result object has errors
     * 
     * @return True if the result object has errors, otherwise false
     */
    public boolean hasErrors() {
        return err.isEmpty();
    }

    /**
     * Gets the response data
     * 
     * @return Response data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * Adds the given errors to this result object
     * 
     * @param err Errors
     */
    public void addErrors(Map<String, Object> err) {
        this.err.putAll(err);
    }

    /**
     * Adds the given data map to this result object
     * 
     * @param data Data
     */
    public void addData(Map<String, Object> data) {
        this.data.putAll(data);
    }

    /**
     * Puts an error into the result object
     * 
     * @param key Error key
     * @param value Error value
     */
    public void putError(String key, Object value) {
        err.put(key, value);
    }

    /**
     * Puts a data entry into the result object
     * 
     * @param key Data key
     * @param value Data value
     */
    public void putData(String key, Object value) {
        err.put(key, value);
    }

    /**
     * Empty response payload constructor
     */
    public ResultObject() {
    }

    /**
     * Constructs a response payload
     * 
     * @param err Response error or null
     * @param data Response data
     */
    public ResultObject(Map<String, Object> err, Map<String, Object> data) {
        this.err = err;
        this.data = data;
    }

}
