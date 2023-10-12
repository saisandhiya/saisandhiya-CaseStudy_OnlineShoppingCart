package com.shoppingcart.order;

import com.shoppingcart.order.controller.OrderController;
import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.entity.Orders;
import com.shoppingcart.order.exception.AddressNotFoundException;
import com.shoppingcart.order.exception.OrdersNotFoundException;
import com.shoppingcart.order.repository.AddressRepository;
import com.shoppingcart.order.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        List<Orders> ordersList = new ArrayList<>();
        when(orderService.getAllOrders()).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Orders>> response = orderController.getAllOrders();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ordersList, response.getBody());
    }

    @Test
    public void testGetAllAddressByCustomerName() throws AddressNotFoundException {
        // Arrange
        List<Address> addressList = new ArrayList<>();
        when(orderService.getAllAddressByCustomerName("john")).thenReturn(addressList);

        // Act
        ResponseEntity<List<Address>> response = orderController.getAllAddressByCustomerName("john");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addressList, response.getBody());
    }

    @Test
    public void testGetOrderByOrderId() throws OrdersNotFoundException {
        // Arrange
        int orderId = 1;
        Orders order = new Orders();
        when(orderService.getOrderByOrderId(orderId)).thenReturn(order);

        // Act
        ResponseEntity<Orders> response = orderController.getOrderByOrderId(orderId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    public void testGetOrderByCustomerName() throws AddressNotFoundException {
        // Arrange
       
        List<Orders> ordersList = new ArrayList<>();
        when(orderService.getAllOrdersByCustomerName("john")).thenReturn(ordersList);

        // Act
        ResponseEntity<List<Orders>> response = orderController.getOrdersByAddress("john");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ordersList, response.getBody());
    }

	
   
    @Test
    public void testPlaceOrders() throws OrdersNotFoundException {
        // Arrange
    	int orderId =11;
        Orders order = new Orders();
        when(orderService.placeorders(order, orderId)).thenReturn(order);

        // Act
        ResponseEntity<Orders> response = orderController.placeorders(order, orderId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    public void testStoreAddress() {
        // Arrange
        Address address = new Address();
        when(orderService.storeAddress(address)).thenReturn(address);

        // Act
        ResponseEntity<Address> response = orderController.storeAddress(address);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(address, response.getBody());
    }

    @Test
    public void testChangeOrderStatus() {
        // Arrange
        int orderId = 1;
        String status = "Shipped";
        Orders order = new Orders();
        when(orderService.changeOrderStatus(status, orderId)).thenReturn(order);

        // Act
        ResponseEntity<Orders> response = orderController.changeOrderStatus(status, orderId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    public void testDeleteOrderById() throws OrdersNotFoundException {
        // Arrange
        int orderId = 1;
        when(orderService.deleteOrder(orderId)).thenReturn("Order deleted successfully");

        // Act
        ResponseEntity<String> response = orderController.deleteOrderById(orderId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());
    }
    
    @Test
    public void testGetAllAddress() {
        // Arrange
        List<Address> addressList = new ArrayList<>();
        when(orderService.getAllAddress()).thenReturn(addressList);

        // Act
        ResponseEntity<List<Address>> response = orderController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addressList, response.getBody());
    }
    
    @Test
    public void testDeleteAddress() throws OrdersNotFoundException {
        // Arrange
        int orderId = 1;
        when(orderService.deleteAddress(orderId)).thenReturn("Address deleted successfully");

        // Act
        ResponseEntity<String> response = orderController.deletedAddress(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Address deleted successfully", response.getBody());
    }
}

