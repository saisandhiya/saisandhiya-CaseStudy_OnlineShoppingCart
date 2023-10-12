package com.shoppingcart.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.shoppingcart.order.controller.PaymentInterface;
import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.entity.Cart;
import com.shoppingcart.order.entity.Orders;
import com.shoppingcart.order.exception.AddressNotFoundException;
import com.shoppingcart.order.exception.OrdersNotFoundException;
import com.shoppingcart.order.repository.AddressRepository;
import com.shoppingcart.order.repository.OrderRepository;
import com.shoppingcart.order.service.OrderServiceImpl;

public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PaymentInterface paymentInterface;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrders() throws OrdersNotFoundException {
        List<Orders> ordersList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(ordersList);
        List<Orders> result = orderService.getAllOrders();
        assertEquals(ordersList, result);
    }

    @Test
    public void testGetAllAddressByCustomerName() throws AddressNotFoundException {
        String customerName = "TestCustomer";
        List<Address> addressList = new ArrayList<>();
        when(addressRepository.findAllByCustomerName(customerName)).thenReturn(addressList);
        List<Address> result = orderService.getAllAddressByCustomerName(customerName);
        assertEquals(addressList, result);
    }

    @Test
    public void testGetOrderByOrderId() throws OrdersNotFoundException {
        int orderId = 1;
        Orders testOrder = new Orders();
        when(orderRepository.findByOrderId(orderId)).thenReturn(Optional.of(testOrder));
        Orders result = orderService.getOrderByOrderId(orderId);
        assertEquals(testOrder, result);
    }

    @Test
    public void testStoreAddress() {
        Address testAddress = new Address();
        when(addressRepository.findById(testAddress.getCustomerId())).thenReturn(Optional.empty());
        when(addressRepository.save(testAddress)).thenReturn(testAddress);
        Address result = orderService.storeAddress(testAddress);
        assertEquals(testAddress, result);
    }

    @Test
    public void testChangeOrderStatus() {
        int orderId = 1;
        String newStatus = "Shipped";
        Orders testOrder = new Orders();
        when(orderRepository.findByOrderId(orderId)).thenReturn(Optional.of(testOrder));
        Orders result = orderService.changeOrderStatus(newStatus, orderId);
        assertEquals(newStatus, result.getOrderStatus());
    }

    @Test
    public void testDeleteOrder() throws OrdersNotFoundException {
        int orderId = 1;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Orders()));
        String result = orderService.deleteOrder(orderId);
        assertEquals("Deleted Sucessfully", result);
    }

    @Test
    public void testPlaceOrders() {
        int cartId = 1;
        Cart testCart = new Cart();
        testCart.setTotalPrice(100.0);
        Orders testOrder = new Orders();
        when(orderRepository.findById(testOrder.getOrderId())).thenReturn(Optional.empty());
        when(paymentInterface.getCartById(cartId)).thenReturn(ResponseEntity.ok(testCart));
        when(orderRepository.insert(testOrder)).thenReturn(testOrder);
        Orders result = orderService.placeorders(testOrder, cartId);
        assertEquals(testOrder, result);
    }
    @Test
    public void testGetAllOrdersByCustomerName() throws OrdersNotFoundException {
        String customerName = "TestCustomer";
        List<Orders> ordersList = new ArrayList<>();
        when(orderRepository.findAllByCustomerName(customerName)).thenReturn(ordersList);
        List<Orders> result = orderService.getAllOrdersByCustomerName(customerName);
        assertEquals(ordersList, result);
    }

    @Test
    public void testGetAllAddress() {
        List<Address> addressList = new ArrayList<>();
        when(addressRepository.findAll()).thenReturn(addressList);
        List<Address> result = orderService.getAllAddress();
        assertEquals(addressList, result);
    }

    @Test
    public void testDeleteAddress() {
        int addressId = 1;
        Address testAddress = new Address();
        when(addressRepository.findByCustomerId(addressId)).thenReturn(testAddress);
        String result = orderService.deleteAddress(addressId);
        assertEquals("Address Deleted", result);
    }

   
}
