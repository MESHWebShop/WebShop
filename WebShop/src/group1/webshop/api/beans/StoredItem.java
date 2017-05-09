package group1.webshop.api.beans;

/**
 * Stored item skeleton class
 */
public class StoredItem {

    /**
     * Item ID as represented in the database
     */
    private int id;

    /**
     * Retrieves the item ID represented in the database
     */
    public int getId() {
	return id;
    }

    /**
     * Sets the item ID represented in the datbase
     */
    public void setId(int id) {
    	this.id = id;
    }

}
