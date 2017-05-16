package group1.webshop.api.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group1.webshop.api.AccountInterface;
import group1.webshop.api.HttpInterface;
import group1.webshop.api.ResponsePayload;
import group1.webshop.api.validators.AccountRegistrationValidator;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/api/account")
public class AccountServlet extends HttpServlet {

    private static final long serialVersionUID = -7885498139672833508L;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> jsonContent = null;

        if ((jsonContent = HttpInterface.validateAndRespond(request, response,
                new AccountRegistrationValidator())) != null) {
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
        }
    }

}
