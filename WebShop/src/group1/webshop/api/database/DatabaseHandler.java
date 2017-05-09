package group1.webshop.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import group1.webshop.api.beans.*;

public class DatabaseHandler {

	static final String user = "root";
	static final String password = "root";
	static final String dbUri = "jdbc:mysql://localhost:3306/meshwebshop?useSSL=false";
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUri, user, password);
	}
	
	//ResultSet kommer att stängas. Hantera genom att stänga ResultSet i den anropande metoden.
	private ResultSet executeQuery(String query) {
		
		ResultSet rs = null;
		
		try (Connection cn = getConnection()) {
			PreparedStatement pstmt = cn.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error: " + e);
		}

		return rs;
	}
	
	public Product getProductByName (String name, int price, String description) throws ClassNotFoundException, SQLException {
		Product product = new Product();
		ResultSet rs = executeQuery("SELECT * FROM product");
		
		rs.next();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getDouble("price"));
		product.setManufacturer(rs.getString("manufacturer"));
		
		return product;
	}
	
}
