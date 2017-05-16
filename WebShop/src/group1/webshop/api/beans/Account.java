package group1.webshop.api.beans;

/**
 * Account bean
 *
 * @author Emil Bertilsson
 */
public class Account {

    /**
     * Username
     */
    private String username;

    /**
     * Email
     */
    private String email;

    /**
     * Password
     */
    private String password;

    /**
     * Retrieves the username
     * 
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the email
     * 
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the password
     * 
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username
     * 
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the email
     * 
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password
     * 
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Empty constructor
     */
    public Account() {
    }

    /**
     * Creates an account with defined values
     * 
     * @param username Username
     * @param email Email
     * @param password Password
     */
    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
