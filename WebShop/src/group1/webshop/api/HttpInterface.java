package group1.webshop.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonSyntaxException;

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
    public Map<String, Object> validateJsonMap(JsonMapValidator validator, Map<String, Object> jsonMap) {
        return validator.validate(jsonMap);
    }

    /**
     * Sends a JSON encoded text as a response with given status and payload to
     * the client
     * 
     * @param response HTTP response object
     * @param status HTTP status code
     * @param payload Response payload
     * @throws IOException IO Error
     */
    public static void respond(HttpServletResponse response, int status, ResponsePayload payload)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(JsonInterface.toJson(payload));
    }

    /**
     * Attempts to read the request content
     * 
     * @param request HTTP request object
     * @return Content or null on failure
     * @throws IOException IO Error
     */
    public static String readContent(HttpServletRequest request)
            throws IOException {
        final BufferedReader reader = request.getReader();
        final StringBuffer str = new StringBuffer();

        int chr;

        while ((chr = reader.read()) != -1) {
            str.append((char) chr);
        }

        if (str.length() > 0) {
            return str.toString();
        } else {
            return null;
        }
    }

    /**
     * Validates the given request as JSON, ensuring the content type and
     * returns either the validated JSON data map or null on failure
     * 
     * @param request HTTP request object
     * @return JSON data map or null on failure
     * @throws IOException IO error
     */
    public static Map<String, Object> validateAsJson(HttpServletRequest request)
            throws IOException {
        String content = null;
        if (request.getContentType().equals("application/json")
                && (content = readContent(request)) != null) {
            try {
                return JsonInterface.fromJson(content);
            } catch (JsonSyntaxException e) {
                return null;
            }
        } else {
            return null;
        }
    }

}
