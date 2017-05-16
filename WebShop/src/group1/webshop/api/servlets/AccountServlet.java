package group1.webshop.api.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group1.webshop.api.AccountInterface;
import group1.webshop.api.HttpInterface;
import group1.webshop.api.JsonInterface;
import group1.webshop.api.ResponsePayload;
import group1.webshop.api.validators.AccountRegistrationValidator;
import group1.webshop.api.validators.JsonMapValidator;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Account registration
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> jsonContent = null;
        JsonMapValidator validator = new AccountRegistrationValidator();

        if ((jsonContent = HttpInterface.validateAsJson(request)) != null) {
            final Map<String, Object> errObject = validator.validate(jsonContent);

            if (errObject.isEmpty()) {
                // Free of errors, proceed to further processing
                final String username = (String) jsonContent.get("username");
                final String email = (String) jsonContent.get("email");
                final String password = (String) jsonContent.get("password");

                // Attempts to register the account
                final Map<String, Object> registerErr = AccountInterface.tryRegister(username, email, password);

                int status = 201; // 201: Created

                // Alter the HTTP status in case of conflict
                if (!registerErr.isEmpty()) {
                    status = 409; // 409: Conflict
                }

                HttpInterface.respond(response,
                        status,
                        new ResponsePayload(registerErr.isEmpty() ? null : registerErr, null));
            } else {
                // Contains errors, return the errors to the client
                final Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("input", errObject);

                HttpInterface.respond(response,
                        422, // 422: Unprocessable entity
                        new ResponsePayload(tempMap, null));
            }
        } else {
            // Notify the source of the invalid request
            HttpInterface.respond(response,
                    400, // 400: Bad request
                    new ResponsePayload("ERR_INVALIDREQUEST", null));
        }
    }

}
