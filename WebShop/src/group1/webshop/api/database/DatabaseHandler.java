package group1.webshop.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    static final String user = "root";
    static final String password = "root";
    static final String dbUri = "jdbc:mysql://localhost:3306/meshwebshop?useSSL=false";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(dbUri, user, password);
    }

}
