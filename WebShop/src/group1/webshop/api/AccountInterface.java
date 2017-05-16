package group1.webshop.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import group1.webshop.api.beans.Account;
import group1.webshop.api.database.DatabaseHandler;

/**
 * Account interface class
 * 
 * @author Emil Bertilsson
 */
public class AccountInterface {

    /**
     * Registers an account and returns the Account ID
     * 
     * @param username Username
     * @param email Email
     * @param password Password
     * @return Account ID on success or -1 on failure
     * @throws SQLException SQL Error
     */
    private static void register(Account account)
            throws SQLException {
        final DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.addAccount(account);
    }

    /**
     * Attempts to register an account to the database
     * (Does not validate the input, needs to be validated in a precondition!)
     * 
     * @param username Username
     * @param email Email
     * @param password Password
     * @return Error map
     */
    public static ResultObject register(String username, String email, String password) {
        final ResultObject result = new ResultObject();
        final DatabaseHandler dbHandler = new DatabaseHandler();

        boolean exists = true;

        try {
            exists = dbHandler.accountExists(username);

            if (!exists) {
                // Account does not exist, attempt register the new account
                Account acc = new Account();
                acc.setUsername(username);
                acc.setEmail(email);
                acc.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

                try {
                    register(acc);
                } catch (SQLException e) {
                    e.printStackTrace();
                    result.putError("ERR_MYSQL", "Server-side MySQL error!");
                }
            } else {
                result.putError("ERR_USEREXISTS", "The user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.putError("ERR_MYSQL", "Server-side MySQL error!");
        }

        return result;
    }

    /**
     * Attempts to authenticate
     * 
     * @param authentication
     * @param password
     * @return
     */
    public static ResultObject authenticate(String authentication, String password) {
        final ResultObject result = new ResultObject();
        final DatabaseHandler dbHandler = new DatabaseHandler();

        return result;
    }

}
