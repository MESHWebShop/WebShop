package group1.webshop.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public Product getProductByName (String name) throws ClassNotFoundException, SQLException {
		
		CachedRowSet crs = callStoredProcedure("get_product_by_name", name);
		crs.next();
		
		Product product = new Product();
		product.setId(crs.getInt("id"));
		product.setName(crs.getString("name"));
		product.setDescription(crs.getString("description"));
		product.setPrice(crs.getDouble("price"));
		product.setManufacturer(crs.getString("manafacturer"));
		
		return product;
	}
	
	/**
	 * Testklass
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		DatabaseHandler db = new DatabaseHandler();
		Product product = null;
		try {
			product = db.getProductByName("produkt2");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(product.getName());

	}

	public void removeProductFromCartById(int productId) {
		// TODO Auto-generated method stub
		
	}
	
}
