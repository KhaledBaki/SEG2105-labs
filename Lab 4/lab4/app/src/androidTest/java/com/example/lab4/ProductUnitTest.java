package com.example.lab4;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductUnitTest {

    @Test
    public void testProductGettersAndSetters() {
        // Creating a test product
        Product product = new Product("product1", "Laptop", 999.99);

        // Verify the constructor and getters work correctly
        assertEquals("product1", product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice(), 0.001);
    }

    @Test
    public void testProductInvalidPrice() {
        Product product = new Product("product2", "Mouse", 15.50);

        // Asserting that the product name is not empty
        assertFalse(product.getName().isEmpty());

        // Verifying that the price is greater than zero
        assertTrue(product.getPrice() > 0);
    }
}