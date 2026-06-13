package com.example.lab4; // Keep whatever your package name is here!

public class Product {
    private String id;
    private String name;
    private double price;

    // Default constructor is required for Firebase to work!
    public Product() {
    }

    // Constructor with parameters
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
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