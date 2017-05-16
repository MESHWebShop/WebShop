package group1.webshop.api.beans;

/**
 * Product image bean class
 */
public class ProductImage extends StoredItem {

    /**
     * Image URL
     */
    private String url;

    /**
     * Image type
     */
    private char type;

    /**
     * Product image constructor
     *
     * @param url Image URL
     * @param type Image type
     */
    public ProductImage(String url, char type) {
	this.url = url;
	this.type = type;
    }
    
    /**
     * 
     */
    public ProductImage() {}

    @Override
    public String toString() {
        return "ProductImage [url=" + url + ", type=" + type + "]";
    }

}
