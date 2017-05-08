package group1.webshop.api.beans;

/**
 * Product bean class
 */
public class Product extends StoredItem {

    /**
     * Product name
     */
    private String name;

    /**
     * Product description
     */
    private String description;

    /**
     * Product manafacturer
     */
    private String manufacturer;

    /**
     * Product categories
     */
    private String[] categories = new String[0];

    /**
     * Product images
     */
    private ProductImage[] images = new ProductImage[0];

    /**
     * Product price
     */
    private int price;

    /**
     * Retrieves the product name
     *
     * @return Product name
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Retrieves the product description
     *
     * @return Product description
     */
    public String getDescription() {
    	return description;
    }

    /**
     * Retrieves the product manufacturer
     *
     * @return Product manufacturer
     */
    public String getManufacturer() {
    	return manufacturer;
    }

    /**
     * Retrieves the product categories
     *
     * @return Product categories
     */
    public String[] getCategories() {
    	return categories;
    }

    /**
     * Retrieves the product images
     *
     * @return Product images
     */
    public ProductImage[] getImages() {
    	return images;
    }

    /**
     * Retrieves the product price
     *
     * @return Product price
     */
    public int getPrice() {
    	return price;
    }

    /**
     * Sets the product categories
     *
     * @param categories New product categories
     */
    public void setCategories(String[] categories) {
    	this.categories = categories;
    }

    /**
     * Product constructor
     *
     * @param name Product name
     * @param description Product description
     * @param manafacturer Product manafacturer
     * @param price Product price
     */
    public Product(String name, String description, String manufacturer, int price) {
	this.name = name;
	this.description = description;
	this.manufacturer = manufacturer;
	this.price = price;
    }
    
    /**
     * 
     */
    public Product() {}

}
