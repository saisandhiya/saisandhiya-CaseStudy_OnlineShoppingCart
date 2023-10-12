package com.naidu.login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.naidu.login.Dto.ProductDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.ProductNotFoundException;
import com.naidu.login.Service.ICustomerService;
import com.naidu.login.controller.CustomerController;
import com.naidu.login.token.JwtUtility;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private ICustomerService customerService;

    @Mock
    private JwtUtility jwtUtility;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        String userName = "customer123";
        Login customer = new Login();
        when(customerService.deleteCustomer(userName)).thenReturn(customer);

        // Act
        ResponseEntity<Login> response = customerController.deleteCustomer(userName);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Login customer = new Login();
        when(customerService.updateCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<Login> response = customerController.updateCustomer(customer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testGetEmail() {
        // Arrange
        String userName = "customer123";
        String email = "customer@example.com";
        when(customerService.getCustomerEmail(userName)).thenReturn(email);

        // Act
        ResponseEntity<String> response = customerController.getEmail(userName);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(email, response.getBody());
    }

    @Test
    public void testGetPassword() {
        // Arrange
        String userName = "customer123";
        String password = "password123";
        when(customerService.getCustomerPassword(userName)).thenReturn(password);

        // Act
        ResponseEntity<String> response = customerController.getPassword(userName);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(password, response.getBody());
    }

    @Test
    public void testGetCustomerByEmail() {
        // Arrange
        String email = "customer@example.com";
        Login customer = new Login();
        when(customerService.getCustomerByEmail(email)).thenReturn(customer);

        // Act
        ResponseEntity<Login> response = customerController.getCustomerByEmail(email);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testViewProductById() throws ProductNotFoundException {
        // Arrange
        int productId = 1;
        ProductDto productDto = new ProductDto();
        when(customerService.viewProductById(productId)).thenReturn(productDto);

        // Act
        ResponseEntity<ProductDto> response = customerController.viewProductById(productId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    @Test
    public void testViewAllProducts() {
        // Arrange
        List<ProductDto> productDtoList = new ArrayList<>();
        when(customerService.viewAllProducts()).thenReturn(productDtoList);

        // Act
        ResponseEntity<List<ProductDto>> response = customerController.viewAllProducts();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDtoList, response.getBody());
    }

    @Test
    public void testUpdateCustomerByEmail() {
        // Arrange
        String email = "customer@example.com";
        Login customer = new Login();
        when(customerService.updateCustomerByEmail(email, customer)).thenReturn(customer);

        // Act
        ResponseEntity<Login> response = customerController.updateCustomerByEmail(email, customer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }
}
