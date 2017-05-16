package group1.webshop.api.beans;

import java.util.Arrays;

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
     * Product manufacturer
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
    private double price;

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

    public Product() {
    }

    /**
     * Retrieves the product name
     *
     * @return Product name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the product description
     *
     * @return Product description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the product manufacturer
     *
     * @return Product manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the product categories
     *
     * @param categories New product categories
     */
    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", description=" + description + ", manufacturer=" + manufacturer
                + ", categories=" + Arrays.toString(categories) + ", images=" + Arrays.toString(images) + ", price="
                + price + "]";
    }

}
