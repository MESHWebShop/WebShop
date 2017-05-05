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
	
//	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
//		List<Product> products = new ArrayList<Product>();
//		
//		try (
//			Connection cn = getConnection();
//			PreparedStatement pstmt = cn.prepareStatement("SELECT id, title, author FROM book");
//			ResultSet rs = pstmt.executeQuery();
//			) {
//			
//				while (rs.next()) {
//					Book book = new Book();
//					book.setId(rs.getInt("id"));
//					book.setTitle(rs.getString("title"));
//					book.setAuthor(rs.getString("author"));
//					books.add(book);
//			}
//			return books;
//		}
//	}
//	
//	public boolean addBook(String title, String author) throws SQLException {
//		Connection cn = null;
//		PreparedStatement pstmt = null;
//		boolean retVal = true;
//		try {
//			String sql = "INSERT INTO book VALUES (null, ?, ?)";
//			cn = getConnection();
//			pstmt = cn.prepareStatement(sql);
//			pstmt.setString(1, title);
//			pstmt.setString(2, author);
//			System.out.println(pstmt.executeUpdate());
//		} catch (SQLException | ClassNotFoundException e) {
//			retVal = false;
//			e.printStackTrace();
//		} finally {
//			if (cn != null)
//				cn.close();
//			if (pstmt != null)
//				pstmt.close();
//		}
//		return retVal;
//	}
	
//	public static void main(String[] args) {
//		DatabaseHandler db = new DatabaseHandler();
//		Connection cn = null;
//		try {
//			System.out.println("Kör en insert " + db.addBook("Emil i Lönneberga", "Astrid Lindgren"));
//			//cn = db.getConnection();
//			//System.out.println("Cn succeeded!");
//		} catch (SQLException e) {
//			System.out.println("Error " + e.getMessage());
//		} finally {
//			if (cn != null)
//				try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
//		}
//	}
}
