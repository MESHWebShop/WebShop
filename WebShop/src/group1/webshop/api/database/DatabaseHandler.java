package group1.webshop.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import group1.webshop.api.beans.Account;
import group1.webshop.api.beans.Product;

public class DatabaseHandler {

    static final String user = "root";
    static final String password = "root";
    static final String dbUri = "jdbc:mysql://localhost:3306/webshop?useSSL=false";

    /**
     * 
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        /*
         * TODO: (Tillagd av emil) Fixa alternativt sätt att upptäcka om
         * drivrutinerna existerar då det blir jobbigt många ClassNotFound
         * exceptionshanterare annars!
         * 
         * Class.forName("com.mysql.jdbc.Driver");
         */
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(dbUri, user, password);
    }

    //TODO: Stäng ResultSet m.m.
    private CachedRowSet executeQuery(String query)
            throws SQLException {
        CachedRowSet crs = null;
        ResultSet rs = null;

        try (Connection cn = getConnection()) {
            PreparedStatement pstmt = cn.prepareStatement(query);
            rs = pstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();

            try {
                crs.populate(rs);
            } catch (SQLException e) {
            }
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

    /**
     * Executes a prepared query with a single parameter
     * 
     * @param query Query string
     * @param parameter Parameter
     * @return Cached result set
     * @throws SQLException SQL Error
     */
    private CachedRowSet executePreparedQuery(String query, Object parameter)
            throws SQLException {
        CachedRowSet crs = null;
        ResultSet rs = null;

        try (Connection cn = getConnection()) {
            PreparedStatement pstmt = cn.prepareStatement(query);
            pstmt.setObject(1, parameter);
            rs = pstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();

            try {
                crs.populate(rs);
            } catch (SQLException e) {
            }
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

    /**
     * Executes a prepared query with many parameters
     * 
     * @param query Query string
     * @param parameters Parameters
     * @return Cached result set
     * @throws SQLException SQL Error
     */
    private CachedRowSet executePreparedQuery(String query, Object[] parameters)
            throws SQLException {
        CachedRowSet crs = null;
        ResultSet rs = null;

        try (Connection cn = getConnection()) {
            PreparedStatement pstmt = cn.prepareStatement(query);

            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }

            rs = pstmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();

            try {
                crs.populate(rs);
            } catch (SQLException e) {
            }
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

    //TODO: Stäng ResultSet m.m.
    private void executeUpdate(String update)
            throws SQLException {
        PreparedStatement pstmt = null;

        try (Connection cn = getConnection()) {
            pstmt = cn.prepareStatement(update);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
        }
    }

    /**
     * Calls a stored procedure with given parameters
     * 
     * @param storedProcedure Stored procedure
     * @param parameters Parameters
     * @return Cached row set
     * @throws SQLException SQL Error
     */
    public CachedRowSet callStoredProcedure(String storedProcedure, Object[] parameters)
            throws SQLException {
        StringBuilder args = new StringBuilder();

        for (int i = 0; i < parameters.length; i++) {
            args.append('?');

            if (i < parameters.length - 1) {
                args.append(',');
            }
        }

        return executePreparedQuery("CALL "
                + storedProcedure
                + "(" + args.toString() + ")", parameters);
    }

    /**
     * Calls a stored procedure
     * 
     * @param storedProcedure Stored procedure
     * @param parameter Parameter
     * @return Cached result set
     * @throws SQLException SQL Error
     */
    public CachedRowSet callStoredProcedure(String storedProcedure, Object parameter)
            throws SQLException {
        return executePreparedQuery("CALL "
                + storedProcedure
                + "(?)", parameter);
    }

    public CachedRowSet callStoredProcedure(String storedProcedure)
            throws SQLException {
        CachedRowSet crs = callStoredProcedure(storedProcedure, "");
        return crs;
    }

    public Product getProductByName(String name) throws ClassNotFoundException, SQLException {

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
     * Tests if an account exists in the database
     * 
     * @param authentication Username or email
     * @return True if the account exists, otherwise false
     * @throws SQLException SQL Error
     */
    public boolean accountExists(String authentication) throws SQLException {
        CachedRowSet crs = callStoredProcedure("account_exists", authentication);

        if (crs.next()) {
            return crs.getBoolean(1);
        } else {
            return false;
        }
    }

    public ArrayList<Product> getAllProducts()
            throws SQLException {
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

    public ArrayList<Product> getAllProductsInCart(int cartId)
            throws SQLException {
        CachedRowSet crs = callStoredProcedure("get_all_products_from_cart", cartId);
        //		CachedRowSet crs = executeQuery("SELECT * FROM cart_product");
        
        ArrayList<Product> products = new ArrayList<Product>();
       
        while (crs.next()) {
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
    
    public void removeProduct(int productId)
            throws SQLException {
        CachedRowSet crs = callStoredProcedure("delete_product_by_id", productId);
    }

    public void removeProductFromCartById(int productId, int cartId)
            throws SQLException {
        CachedRowSet crs = callStoredProcedure("delete_cart_product_by_id", productId);
    }

    /**
     * Adds an account to the database
     * 
     * @param account Account
     * @return Account ID or null on failure
     */
    public CachedRowSet addAccount(Account account)
            throws SQLException {
        return callStoredProcedure("add_account", new Object[] {
                null,
                account.getUsername(),
                account.getEmail(),
                account.getPassword()
        });
    }

    public void addProductToCart(String productId, String cartId, String count) {
//    	new Object[] {
//                null,
//                account.getUsername(),
//                account.getEmail(),
//                account.getPassword()
//        }
    	// Läs in count för en specifik product_cart-post
        CachedRowSet crs = null;
		try {
			crs = callStoredProcedure("get_count_from_cart_product", new Object[] {productId, cartId});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        int countFromCartProduct = 0;
        try {
            crs.first();
            countFromCartProduct = crs.getInt("count");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
        	// Om posten finns, addera 1 till count.
            if (crs.first()) {
                System.out.println("Count: " + crs.getInt("count"));
                countFromCartProduct = crs.getInt("count");
                executeUpdate("UPDATE cart_product SET count = " + (countFromCartProduct + 1) +
                        " WHERE product_id = " + productId + " AND cart_id = " + cartId);
            
            // Om posten inte finns, skapa en ny cart_product.
            } else {
                System.out.println("Else-satsen körs.");
                executeUpdate(
                        "INSERT INTO cart_product VALUES (null, " + productId + ", " + cartId + ", " + count + ")");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Gets an account from the database
     * 
     * @param authentication Username or email
     * @return Account object
     * @throws SQLException
     */
    public Account getAccount(String authentication)
            throws SQLException {
        CachedRowSet crs = callStoredProcedure("get_account_by_authentication", authentication);

        final Account account = new Account();

        if (crs.next()) {
            account.setId(crs.getInt("id"));
            account.setUsername(crs.getString("username"));
            account.setPassword(crs.getString("password"));
            account.setEmail(crs.getString("email"));
        }

        return account;
    }

    /**
     * Gets an account from the database
     * 
     * @param id Account id
     * @return Account object
     * @throws SQLException
     */
    public Account getAccount(int id)
            throws SQLException {
        CachedRowSet crs = callStoredProcedure("get_account_by_id", id);

        final Account account = new Account();

        if (crs.next()) {
            account.setId(crs.getInt("id"));
            account.setUsername(crs.getString("username"));
            account.setPassword(crs.getString("password"));
            account.setEmail(crs.getString("email"));
        }

        return account;
    }

    /**
     * TODO: (Tillagd av emil)
     * Skriv om detta som ett test case i api.tests
     */
        public static void main(String[] args) throws SQLException {
            DatabaseHandler db = new DatabaseHandler();
            
            Connection cn = db.getConnection();
            if (cn != null) {
            	System.out.println("Connection successful.");
            } else {
            	System.out.println("Connection not successful.");
            }
            
            db.addProductToCart("3", "1", "1");
            //		db.addProductToCart("4", "1", "1");
            //db.addProductToCart("3", "1", "1");
    
            //		ArrayList<Product> products = db.getAllProductsInCart(1);
            //		System.out.println(products.size());
    
            //		Product product = null;
            //		ArrayList<Product> products = null;
            //		
            //		try {
            //			product = db.getProductByName("skruvar");
            //			products = db.getAllProducts();
            //		} catch (ClassNotFoundException e1) {
            //			e1.printStackTrace();
            //		}
            //		
            //		for(Product p : products) {
            //			System.out.println(p.getName());
            //		}
            //		
            //		System.out.println(product.getName());
        }
    
}
