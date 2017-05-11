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
 * Servlet implementation class RemoveProductFromCartServlet
 */
@WebServlet("/RemoveProductFromCart")
public class RemoveProductFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveProductFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	    	DatabaseHandler db = new DatabaseHandler();
	    	    	
	    	    	int productId = Integer.valueOf(request.getParameter("id"));
	    	    	
	    	    	try {
	    				db.removeProductFromCartById(productId);
	    			} catch (ClassNotFoundException | SQLException e) {
	    				e.printStackTrace();
	    			}
	    	    	
	    	    	response.getWriter().write("Hejsan, du har kommit till RemoveProductFromCartServlet.");

//	    	    	request.setAttribute("productName", product.getName());
//	    	    	response.getWriter().append(productName);
	    		
	    	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
