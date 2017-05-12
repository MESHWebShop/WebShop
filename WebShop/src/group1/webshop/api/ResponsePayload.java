package group1.webshop.api;

/**
 * Response payload class
 * 
 * @author Emil Bertilsson
 */
public class ResponsePayload {

    /**
     * Response error
     */
    private String err;

    /**
     * Response content
     */
    private Object data;

    /**
     * Gets the response error or null if there is no error
     * 
     * @return Response error
     */
    public String getError() {
        return err;
    }

    /**
     * Gets the response data
     * 
     * @return Response data
     */
    public Object getData() {
        return data;
    }

    /**
     * Empty response payload constructor
     */
    public ResponsePayload() {
    }

    /**
     * Constructs a response payload
     * 
     * @param err Response error or null
     * @param data Response data
     */
    public ResponsePayload(String err, Object data) {
        this.err = err;
        this.data = data;
    }

}
