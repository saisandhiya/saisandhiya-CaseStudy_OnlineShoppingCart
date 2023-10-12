package com.OnlineShoppingCart;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.OnlineShoppingCart.Controller.ProductController;
import com.OnlineShoppingCart.Entity.Product;
import com.OnlineShoppingCart.Exceptions.ProductNotFoundException;
import com.OnlineShoppingCart.ServiceImpl.ProductServiceImpl;

class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // Initialize mock behavior here
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct_Successful() {
        // Arrange
        Product productToAdd = new Product();
        productToAdd.setProductId(1);

        when(productService.addProduct(productToAdd)).thenReturn(productToAdd);

        // Act
        ResponseEntity<Product> response = productController.addProduct(productToAdd);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productToAdd, response.getBody());
    }

   
    @Test
    void testGetAllProduct_Successful() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        //productList.add(new Product());
       // productList.add(new Product());

        when(productService.getAllProduct()).thenReturn(productList);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProduct();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productList, response.getBody());
    }

    @Test
    void testGetProductById_ProductFound() throws ProductNotFoundException {
        // Arrange
        int productId = 1;
        Product product = new Product();
        product.setProductId(productId);

        when(productService.getProductById(productId)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(product, response.getBody());
    }
    
    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> ordersList = new ArrayList<>();
        when(productService.getAllProduct()).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProduct();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ordersList, response.getBody());
    }
    
    @Test
    void testGetProductByName_ProductFound() throws ProductNotFoundException {
        // Arrange
             
        List<Product> ordersList = new ArrayList<>();
        when(productService.getAllProductsByName("john")).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProductByName("john");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ordersList, response.getBody());
    }
    
    

    @Test
    void testGetProductById_ProductNotFound() throws ProductNotFoundException {
        // Arrange
        int productId = 1;

        when(productService.getProductById(productId)).thenThrow(new ProductNotFoundException("not found"));

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(productId));
    }
    
    

    @Test
    void testUpdateProducts_Successful() {
        // Arrange
        int productId = 1;
        Product productToUpdate = new Product();
        productToUpdate.setProductId(productId);

        when(productService.updateProducts(productId, productToUpdate)).thenReturn(productToUpdate);

        // Act
        ResponseEntity<Product> response = productController.updateProducts(productId, productToUpdate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productToUpdate, response.getBody());
    }

    @Test
    void testUpdateProducts_ProductNotFound() {
        // Arrange
        int productId = 1;
        Product productToUpdate = new Product();
        productToUpdate.setProductId(productId);

        when(productService.updateProducts(productId, productToUpdate)).thenThrow(new ProductNotFoundException("not found"));

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productController.updateProducts(productId, productToUpdate));
    }

    @Test
    void testDeleteProductById_Successful() {
        // Arrange
        int productId = 1;
        when(productService.deleteProductById(productId)).thenReturn("Product deleted successfully");


        // Act
        ResponseEntity<String> response = productController.deleteProductById(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }

    @Test
    void testDeleteProductById_ProductNotFound() {
        // Arrange
        int productId = 1;

        when(productService.deleteProductById(productId)).thenThrow(new ProductNotFoundException("not found"));

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productController.deleteProductById(productId));
    }
    
    @Test
    void testGetProductByCategory() throws ProductNotFoundException {
        // Arrange
             
        List<Product> ordersList = new ArrayList<>();
        when(productService.getProductByCategory("laptop")).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Product>> response = productController.getProductByCategory("laptop");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ordersList, response.getBody());
    }
    
    @Test
    void testGetProductByType() throws ProductNotFoundException {
        // Arrange
             
        List<Product> ordersList = new ArrayList<>();
        when(productService.getProductByType("laptop")).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Product>> response = productController.getProductByType("laptop");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ordersList, response.getBody());
    }

    }
