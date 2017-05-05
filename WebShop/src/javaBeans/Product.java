package javaBeans;

public class Product {

    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productManufactureId;
    private String taxClassId;
    private int manufacturerId;

    public String getProductId() {
        return productId;
    }

//    public String toString() {
//        return productId + ": " + productName + ": " + productDescription + ": " + productPrice + ".";
//    }

    public void setProductId(String productId) {
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

    public String getProductManufactureId() {
        return productManufactureId;
    }

    public void setProductManufactureId(String productManufactureId) {
        this.productManufactureId = productManufactureId;
    }

    public String getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(String taxClassId) {
        this.taxClassId = taxClassId;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Product() {
    }

}
