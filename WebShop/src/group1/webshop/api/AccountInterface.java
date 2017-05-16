package group1.webshop.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

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
    private static int register(Account account)
            throws SQLException {
        final DatabaseHandler dbHandler = new DatabaseHandler();
        CachedRowSet result = dbHandler.addAccount(account);

        return -1;
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
    public static Map<String, Object> tryRegister(String username, String email, String password) {
        final Map<String, Object> errMap = new HashMap<>();
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

                int uid = -1;

                try {
                    uid = register(acc);
                } catch (SQLException e) {
                    e.printStackTrace();
                    errMap.put("ERR_MYSQL", "Server-side MySQL error!");
                }
            } else {
                errMap.put("ERR_USEREXISTS", "The user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errMap.put("ERR_MYSQL", "Server-side MySQL error!");
        }

        return errMap;
    }

}
