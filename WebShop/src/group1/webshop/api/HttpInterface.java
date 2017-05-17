package group1.webshop.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
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
     * @param payload Result object
     * @throws IOException IO Error
     */
    public static void respond(HttpServletResponse response, int status, ResultObject payload)
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

    /**
     * Validates and responds to the client if their request failed evaluation.
     * On success the JSON data map is returned
     *
     * @param request HTTP request object
     * @param response HTTP response object
     * @param validator JSON map validator
     * 
     * @return JSON data map or null if the request was invalid
     * @throws IOException
     */
    public static Map<String, Object> validateAndRespond(HttpServletRequest request,
            HttpServletResponse response,
            JsonMapValidator validator)
            throws IOException {
        Map<String, Object> jsonContent = null;

        if (request.getContentType().equals("application/json")
                && (jsonContent = HttpInterface.validateAsJson(request)) != null) {
            final Map<String, Object> errObject = validator.validate(jsonContent);

            if (errObject.isEmpty()) {
                // Free of errors, proceed to further processing
                return jsonContent;
            } else {
                // Contains errors, return the errors to the client
                final Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("input", errObject);

                HttpInterface.respond(response,
                        422, // 422: Unprocessable entity
                        new ResultObject(tempMap, null));
                return null;
            }
        } else {
            // Notify the source of the invalid request
            HttpInterface.respond(response,
                    400, // 400: Bad request
                    ResultObject.simpleWithError("ERR_INVALIDREQUEST",
                            "Request is not of type 'application/json' or JSON content is invalid"));
            return null;
        }
    }
}
