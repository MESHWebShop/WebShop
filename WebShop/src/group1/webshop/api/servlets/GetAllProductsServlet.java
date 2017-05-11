package group1.webshop.api.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import group1.webshop.api.beans.Product;
import group1.webshop.api.database.DatabaseHandler;

/**
 * Servlet implementation class GetAllProductsServlet
 */
@WebServlet("/GetAllProducts")
public class GetAllProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DatabaseHandler db = new DatabaseHandler();
    	Product product = null;
    	ArrayList<Product> products = new ArrayList<Product>;
    	
    	try {
			products = db.getAllProducts();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	
    	String json = new Gson().toJson(product);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
