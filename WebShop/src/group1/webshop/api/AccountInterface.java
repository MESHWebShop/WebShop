package group1.webshop.api;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import group1.webshop.api.beans.Account;
import group1.webshop.api.database.DatabaseHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * Account interface class
 * 
 * @author Emil Bertilsson
 */
public class AccountInterface {

    private final static String BCRYPT_SALT = BCrypt.gensalt();
    private final static String JWT_SECRET = "grupp1webshop";
    private static final long JWT_EXPIRATION_HOURS = 24;

    /**
     * Registers an account and returns the Account ID
     * 
     * @param username Username
     * @param email Email
     * @param password Password
     * @return Account ID on success or -1 on failure
     * @throws SQLException SQL Error
     */
    private static int doRegister(Account account)
            throws SQLException {
        final DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.addAccount(account);

        // TODO: Implement a way to retrieve the id column from the created row
        return 0;
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
                acc.setPassword(BCrypt.hashpw(password, BCRYPT_SALT));

                try {
                    doRegister(acc);
                } catch (SQLException e) {
                    e.printStackTrace();
                    result.putError("ERR_MYSQL", "Server-side MySQL error");
                }
            } else {
                result.putError("ERR_USEREXISTS", "The user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.putError("ERR_MYSQL", "Server-side MySQL error");
        }

        return result;
    }

    /**
     * Attempts to authenticate
     * 
     * @param authentication Username or email
     * @param password Password
     * @return Authentication result
     */
    public static ResultObject authenticate(String authentication, String password) {
        final ResultObject result = new ResultObject();
        final DatabaseHandler dbHandler = new DatabaseHandler();

        boolean exists = true;

        try {
            exists = dbHandler.accountExists(authentication);

            if (exists) {
                // Account exists
                final Account account = dbHandler.getAccount(authentication);

                if (BCrypt.checkpw(password, account.getPassword())) {
                    // The authentication is valid, now create an authentication token
                    OffsetDateTime expiry = OffsetDateTime.now().plusHours(JWT_EXPIRATION_HOURS);
                    Date expiryDate = Date.from(expiry.toInstant());

                    String token = Jwts.builder()
                            .setExpiration(expiryDate)
                            .claim("id", account.getId())
                            .signWith(
                                    SignatureAlgorithm.HS256,
                                    JWT_SECRET.getBytes())
                            .compact();

                    result.putData("token", token);
                } else {
                    result.putError("ERR_AUTH", "Invalid credentials");
                }
            } else {
                result.putError("ERR_NOUSER", "The user doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.putError("ERR_MYSQL", "Server-side MySQL error");
        }

        return result;
    }

    /**
     * Attempts to return the claims of this token
     * 
     * @param token Token
     * @return Token claims
     * @throws IllegalArgumentException Invalid token
     */
    public static Jws<Claims> getTokenClaims(String token)
            throws IllegalArgumentException {
        try {
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET.getBytes())
                    .parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    /**
     * Gets an account object from given token or throws an exception if the
     * token was unable to be decoded
     * 
     * @param token Token
     * @return Account
     * @throws IllegalArgumentException Invalid token
     * @throws SQLException SQL error
     */
    public static Account getAccountFromToken(String token)
            throws IllegalArgumentException, SQLException {
        final DatabaseHandler dbHandler = new DatabaseHandler();
        Jws<Claims> claims = getTokenClaims(token);

        Claims body = claims.getBody();

        if (body.containsKey("id")
                && Integer.class.isInstance(body.get("id"))) {
            return dbHandler.getAccount((int) body.get("id"));
        } else {
            throw new IllegalArgumentException("No integer 'id' claim in token");
        }
    }

}
