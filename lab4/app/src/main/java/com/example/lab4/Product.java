// Importing Lab 4
package com.example.lab4;

public class Product {

    // instance variables
    private String id;
    private String name;
    private double price;

    // Must have non-parameterized constructor for firebase to work
    // Java would have implemented this automatically however
    public Product() {
    }

    /**
     * Parameterized constructor of Product
     *
     * @param id Identification of specific product
     * @param name Name of product
     * @param price Price of product
     */
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}