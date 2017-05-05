package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaBeans.Product;

public class DatabaseHandler {

	static final String user = "root";
	static final String password = "root";
	static final String dbUri = "jdbc:mysql://localhost:3306/servletcodealong?useSSL=false";
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbUri, user, password);
	}
	
	public Product getProductByName (String name, int price, String description) throws ClassNotFoundException, SQLException {
		Product product = new Product();
		
		try (
			Connection cn = getConnection();
			PreparedStatement pstmt = cn.prepareStatement("SELECT id, name, description FROM product WHERE name = " + name);
			ResultSet rs = pstmt.executeQuery();
			) {
					rs.next();
					product.setProductName(rs.getString("name"));
					product.setProductPrice(rs.getInt("price"));
					product.setProductDescription(rs.getString("description"));
			}
		return product;
	}
}
