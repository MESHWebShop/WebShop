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

    static final String user = "webshop";
    static final String password = "webshop";
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

    //	public CachedRowSet callStoredProcedure(String storedProcedure, String argument) {
    //		CachedRowSet crs = executeQuery("CALL " + storedProcedure + "('" + argument + "');");
    //		return crs;
    //	}

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
     * TODO: (Tillagd av emil)
     * Skriv om detta som ett test case i api.tests
     */
    //    public static void main(String[] args) throws SQLException {
    //        DatabaseHandler db = new DatabaseHandler();
    //
    //        //		db.addProductToCart("4", "1", "1");
    //        db.addProductToCart("3", "1", "1");
    //
    //        //		ArrayList<Product> products = db.getAllProductsInCart(1);
    //        //		System.out.println(products.size());
    //
    //        //		Product product = null;
    //        //		ArrayList<Product> products = null;
    //        //		
    //        //		try {
    //        //			product = db.getProductByName("skruvar");
    //        //			products = db.getAllProducts();
    //        //		} catch (ClassNotFoundException e1) {
    //        //			e1.printStackTrace();
    //        //		}
    //        //		
    //        //		for(Product p : products) {
    //        //			System.out.println(p.getName());
    //        //		}
    //        //		
    //        //		System.out.println(product.getName());
    //    }

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
                account.getPassword(),
                account.getEmail()
        });
    }

    public void addProductToCart(String productId, String cartId, String count) throws SQLException {
        //CachedRowSet crs = callStoredProcedure("add_product_to_cart_product", productId + ", " + cartId + ", " + count);
        //callStoredProcedure("get_count_from_cart_product", productId + ", " + cartId + ", " + count);
        //CachedRowSet crs = executeQuery("SELECT count FROM cart_product WHERE product_id = " + productId + " AND cart_id = " + cartId);

        // Kolla count för produkten.
        // Om den finns, uppdatera count.
        // Om den inte finns, lägg till.

        //		executeQuery("CALL add_count_to_cart_product(3, 1, 2, 1);");
        //		System.out.println("Före get_count_from_cart_product");

        CachedRowSet crs = callStoredProcedure("get_count_from_cart_product", productId + ", " + cartId);
        //		System.out.println("Efter get_count_from_cart_product");
        //crs = callStoredProcedure("add_count_to_cart_product", productId + ", " + cartId + ", " + 1 + ", " + cartProductCount);
        //		executeQuery("CALL add_count_to_cart_product(3, 1, 2, 1);");

        int countFromCartProduct = 0;
        try {
            crs.first();
            countFromCartProduct = crs.getInt("count");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //		

        //
        //		int cartProductCount;
        //		
        try {
            if (crs.first()) {
                System.out.println("Count: " + crs.getInt("count"));
                countFromCartProduct = crs.getInt("count");
                //crs = callStoredProcedure("add_count_to_cart_product", productId + ", " + cartId + ", " + 1 + ", " + cartProductCount);
                //executeUpdate("CALL add_count_to_cart_product(4, 1, 2, 1);");
                executeUpdate("UPDATE cart_product SET count = " + (countFromCartProduct + 1) +
                        " WHERE product_id = " + productId + " AND cart_id = " + cartId);
            } else {
                System.out.println("Else-satsen körs.");
                //cartProductCount = 0;
                //String s = "add_product_to_cart_product" + "(" + productId + ", " + cartId + ", " + 1 + ")";
                //System.out.println(s);
                //				callStoredProcedure("add_product_to_cart_product", productId + ", " + cartId + ", " + 1);
                executeUpdate(
                        "INSERT INTO cart_product VALUES (null, " + productId + ", " + cartId + ", " + count + ")");
                //				executeUpdate("call add_product_to_cart_product(4, 1, 1)");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Gammal arbetsgång:
        // * Kolla om produkten existerar
        // * Kolla om varukorgen existerar
        // * Om varukorgen inte existerar, skapa en ny varukorg
        // * Skapa ett nytt cart_product-entry

    }
}
