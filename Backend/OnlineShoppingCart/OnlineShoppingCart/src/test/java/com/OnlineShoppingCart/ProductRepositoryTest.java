package com.OnlineShoppingCart;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.OnlineShoppingCart.Entity.Product;
import com.OnlineShoppingCart.Repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindProductByValidId() {
        // Create a product and save it to the repository
        Product product = new Product(1, "Type1", "Product1", "Category1", "5", "Good product", "image.jpg", 100.0, "Description", "Specs");
        productRepository.save(product);

        // Find the product by its valid ID
        Optional<Product> foundProduct = productRepository.findByProductId(1);

        // Validate that the product is found and its name matches
        assertTrue(foundProduct.isPresent());
        assertEquals("Product1", foundProduct.get().getProductName());
    }

    @Test
    public void testFindProductByInvalidId() {
        // Attempt to find a product with an invalid ID
        Optional<Product> foundProduct = productRepository.findByProductId(999);

        // Validate that the product is not found (Optional is empty)
        assertFalse(foundProduct.isPresent());
    }

    @Test
    public void testFindProductsByProductName() {
        // Create multiple products with the same name
        Product product1 = new Product(1, "Type1", "Product1", "Category1", "5", "Good product", "image.jpg", 100.0, "Description", "Specs");
        Product product2 = new Product(2, "Type2", "Product1", "Category2", "4", "Decent product", "image2.jpg", 50.0, "Description2", "Specs2");
        productRepository.saveAll(List.of(product1, product2));

        // Find products by product name
        List<Product> foundProducts = productRepository.findByProductName("Product1");

        // Validate that both products with the same name are found
        assertEquals(2, foundProducts.size());
        assertEquals("Product1", foundProducts.get(0).getProductName());
        assertEquals("Product1", foundProducts.get(1).getProductName());
    }

    @Test
    public void testFindProductsByCategory() {
        // Create products with different categories
        Product product1 = new Product(1, "Type1", "Product1", "Category1", "5", "Good product", "image.jpg", 100.0, "Description", "Specs");
        Product product2 = new Product(2, "Type2", "Product2", "Category2", "4", "Decent product", "image2.jpg", 50.0, "Description2", "Specs2");
        productRepository.saveAll(List.of(product1, product2));

        // Find products by category
        List<Product> foundProducts = productRepository.findByCategory("Category1");

        // Validate that products with the specified category are found
        assertEquals(1, foundProducts.size());
        assertEquals("Category1", foundProducts.get(0).getCategory());
    }

    @Test
    public void testFindProductsByProductType() {
        // Create products with different product types
        Product product1 = new Product(1, "Type1", "Product1", "Category1", "5", "Good product", "image.jpg", 100.0, "Description", "Specs");
        Product product2 = new Product(2, "Type2", "Product2", "Category2", "4", "Decent product", "image2.jpg", 50.0, "Description2", "Specs2");
        productRepository.saveAll(List.of(product1, product2));

        // Find products by product type
        List<Product> foundProducts = productRepository.findByProductType("Type1");

        // Validate that products with the specified product type are found
        assertEquals(1, foundProducts.size());
        assertEquals("Type1", foundProducts.get(0).getProductType());
    }

    @Test
    public void testFindAllByProductName() {
        // Create multiple products with different names
        Product product1 = new Product(1, "Type1", "Product1", "Category1", "5", "Good product", "image.jpg", 100.0, "Description", "Specs");
        Product product2 = new Product(2, "Type2", "Product2", "Category2", "4", "Decent product", "image2.jpg", 50.0, "Description2", "Specs2");
        productRepository.saveAll(List.of(product1, product2));

        // Find all products by product name
        List<Product> foundProducts = productRepository.findAllByProductName("Product1");

        // Validate that products with the specified name are found
        assertEquals(1, foundProducts.size());
        assertEquals("Product1", foundProducts.get(0).getProductName());
    }
}
