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

import com.naidu.login.Dto.CartDto;
import com.naidu.login.Dto.OrderDto;
import com.naidu.login.Dto.PaymentDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Service.IAdminService;
import com.naidu.login.Service.ICustomerService;
import com.naidu.login.controller.AdminController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private IAdminService adminService;

    @Mock
    private ICustomerService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteAdmin() {
        // Arrange
        String userName = "admin123";
        Login admin = new Login();
        when(adminService.deleteAdmin(userName)).thenReturn(admin);

        // Act
        ResponseEntity<Login> response = adminController.deleteAdmin(userName);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    @Test
    public void testUpdateAdmin() {
        // Arrange
        String userName = "admin123";
        Login admin = new Login();
        when(adminService.updateAdmin(userName, admin)).thenReturn(admin);

        // Act
        ResponseEntity<Login> response = adminController.updateAdmin(userName, admin);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    @Test
    public void testGetCartById() {
        // Arrange
        int cartId = 1;
        CartDto cartDto = new CartDto();
        when(adminService.getCartById(cartId)).thenReturn(cartDto);

        // Act
        ResponseEntity<CartDto> response = adminController.getCartById(cartId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cartDto, response.getBody());
    }

    @Test
    public void testViewAllCarts() {
        // Arrange
        List<CartDto> cartDtoList = new ArrayList<>();
        when(adminService.getAllCarts()).thenReturn(cartDtoList);

        // Act
        ResponseEntity<List<CartDto>> response = adminController.viewAllCarts();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cartDtoList, response.getBody());
    }

    @Test
    public void testGetOrderById() {
        // Arrange
        int orderId = 1;
        OrderDto orderDto = new OrderDto();
        when(adminService.getOrderById(orderId)).thenReturn(orderDto);

        // Act
        ResponseEntity<OrderDto> response = adminController.getOrderById(orderId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderDto, response.getBody());
    }

    @Test
    public void testViewOrders() {
        // Arrange
        List<OrderDto> orderDtoList = new ArrayList<>();
        when(adminService.getAllOrders()).thenReturn(orderDtoList);

        // Act
        ResponseEntity<List<OrderDto>> response = adminController.viewOrders();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderDtoList, response.getBody());
    }

    @Test
    public void testGetPaymentDetails() {
        // Arrange
        int transactionId = 1;
        PaymentDto paymentDto = new PaymentDto();
        when(adminService.getPaymentDetails(transactionId)).thenReturn(paymentDto);

        // Act
        ResponseEntity<PaymentDto> response = adminController.getPaymentDetails(transactionId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(paymentDto, response.getBody());
    }

    @Test
    public void testGetCustomer() {
        // Arrange
        String username = "user123";
        Login customer = new Login();
        when(userService.getCustomerByUserName(username)).thenReturn(customer);

        // Act
        ResponseEntity<Login> response = adminController.getCustomer(username);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testGetCustomers() {
        // Arrange
        List<Login> customers = new ArrayList<>();
        when(userService.getCustomers()).thenReturn(customers);

        // Act
        ResponseEntity<List<Login>> response = adminController.getCustomers();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }
}
