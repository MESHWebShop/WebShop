package javaBeans;

public class Product {

    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;

    public Product() {
    }

//    public String toString() {
//        return productId + ": " + productName + ": " + productDescription + ": " + productPrice + ".";
//    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
