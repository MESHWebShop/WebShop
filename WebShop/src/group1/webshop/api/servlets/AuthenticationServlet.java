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
import group1.webshop.api.validators.AuthenticationValidator;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/api/authentication")
public class AuthenticationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> jsonContent = null;

        if ((jsonContent = HttpInterface.validateAndRespond(request, response,
                new AuthenticationValidator())) != null) {
            final String authentication = (String) jsonContent.get("authentication");
            final String password = (String) jsonContent.get("password");

            System.out.println(AccountInterface.authenticate(authentication, password));
        }
    }

}
