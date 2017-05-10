package group1.webshop.api.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group1.webshop.api.beans.Product;
import group1.webshop.api.database.DatabaseHandler;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product")
public class GetProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    	DatabaseHandler db = new DatabaseHandler();
    	Product product = null;
    	
    	try {
			product = db.getProductByName("produktnamn");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("product", product.getName());
    	
    	response.getWriter().append(product.getName());
	
    }

}
