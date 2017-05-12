package group1.webshop.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import group1.webshop.api.beans.*;

public class DatabaseHandler {
	
	static final String user = "root";
	static final String password = "root";
	static final String dbUri = "jdbc:mysql://localhost:3306/webshop?useSSL=false";
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUri, user, password);
	}
	
	//TODO: Stäng ResultSet m.m.
	private CachedRowSet executeQuery(String query) {
		CachedRowSet crs = null;
		ResultSet rs = null;
			
		try (Connection cn = getConnection()) {
			PreparedStatement pstmt = cn.prepareStatement(query);
			rs = pstmt.executeQuery();
			crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(rs);
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error: " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return crs;
	}
	
	public CachedRowSet callStoredProcedure(String storedProcedure, String argument) {
		CachedRowSet crs = executeQuery("CALL " + storedProcedure + "('" + argument + "');");
		return crs;
	}
	
	public CachedRowSet callStoredProcedure(String storedProcedure) {
		CachedRowSet crs = callStoredProcedure(storedProcedure, "");
		return crs;
	}
	
	public Product getProductByName (String name) throws ClassNotFoundException, SQLException {
		
		CachedRowSet crs = callStoredProcedure("get_product_by_name", name);
		crs.next();
		
		Product product = new Product();
		product.setId(crs.getInt("id"));
		product.setName(crs.getString("name"));
		product.setDescription(crs.getString("description"));
		product.setPrice(crs.getDouble("price"));
		product.setManufacturer(crs.getString("manufacturer"));
		
		return product;
	}
	
	/**
	 * Testklass
	 * @param args
	 * @throws SQLException
	 */

	public boolean removeProductFromCartById(int productId) {
		
		
		CachedRowSet crs = callStoredProcedure("delete_cart_product_by_id", String.valueOf(productId));
		
		return true;
		
	}

	public ArrayList<Product> getAllProducts() throws SQLException {
//		CachedRowSet crs = callStoredProcedure("get_all_products");
		CachedRowSet crs = executeQuery("SELECT * FROM product");
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		while (crs.next() == true) {
			Product product = new Product();
			product.setId(crs.getInt("id"));
			product.setName(crs.getString("name"));
			product.setDescription(crs.getString("description"));
			product.setPrice(crs.getDouble("price"));
			product.setManufacturer(crs.getString("manufacturer"));
			products.add(product);
		}
		
		return products;
	}
	
	public static void main(String[] args) throws SQLException {
		
		DatabaseHandler db = new DatabaseHandler();
		Product product = null;
		ArrayList<Product> products = null;
		
		try {
			product = db.getProductByName("skruvar");
			products = db.getAllProducts();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		for(Product p : products) {
			System.out.println(p.getName());
		}
		
		System.out.println(product.getName());

	}
	
}
