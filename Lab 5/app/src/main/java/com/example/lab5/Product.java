package com.example.lab5;
public class Product {
    private int productID;
    private String name;
    private int SKU;

    public Product(){
        // Empty constructor
    }

    public Product(String name, int SKU){

    }
    /**
     * Product Parameterized Constructor
     *
     * @param productID identification of product
     * @param name of product
     * @param SKU of product
     */
    public Product(int productID, String name, int SKU){
        this.productID = productID;
        this.name = name;
        this.SKU = SKU;
    }

    public int getProductID() {
        return productID;
    }
    public String getName() {
        return name;
    }
    public int getSKU() {
        return SKU;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }
}
