package com.naidu.login;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.naidu.login.Dto.CartDto;
import com.naidu.login.Dto.OrderDto;
import com.naidu.login.Dto.PaymentDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.AdminNotFoundException;
import com.naidu.login.Repository.ICustomerRepostiory;
import com.naidu.login.Service.AdminServiceImpl;

public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private ICustomerRepostiory customerRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddAdmin() throws AdminNotFoundException {
        Login admin = new Login();
        admin.setUserName("admin");
        when(customerRepository.findByUserName(admin.getUserName())).thenReturn(Optional.empty());
        when(customerRepository.save(admin)).thenReturn(admin);

        Login result = adminService.addAdmin(admin);

        assertEquals(admin, result);
    }

    @Test
    public void testAddAdminAdminAlreadyExists() {
        Login admin = new Login();
        admin.setUserName("admin");
        when(customerRepository.findByUserName(admin.getUserName())).thenReturn(Optional.of(admin));

        assertThrows(AdminNotFoundException.class, () -> adminService.addAdmin(admin));
    }

    @Test
    public void testDeleteAdmin() throws AdminNotFoundException {
        String userName = "admin";
        Login admin = new Login();
        admin.setUserName(userName);
        when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(admin));

        Login result = adminService.deleteAdmin(userName);

        assertEquals(admin, result);
    }

    @Test
    public void testDeleteAdminAdminNotFound() {
        String userName = "admin";
        when(customerRepository.findByUserName(userName)).thenReturn(Optional.empty());

        assertThrows(AdminNotFoundException.class, () -> adminService.deleteAdmin(userName));
    }

    @Test
    public void testUpdateAdmin() throws AdminNotFoundException {
        String userName = "admin";
        Login admin = new Login();
        admin.setUserName(userName);

        Login updatedAdmin = new Login();
        updatedAdmin.setUserName(userName);
        updatedAdmin.setFirstName("UpdatedFirstName");
        updatedAdmin.setLastName("UpdatedLastName");
        updatedAdmin.setEmail("updated@example.com");
        updatedAdmin.setMobileNumber("1234567890");
        updatedAdmin.setPassword("newpassword");

        when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(admin));
        when(customerRepository.save(updatedAdmin)).thenReturn(updatedAdmin);

        Login result = adminService.updateAdmin(userName, updatedAdmin);

        assertEquals(updatedAdmin, result);
    }

    @Test
    public void testUpdateAdminAdminNotFound() {
        String userName = "admin";
        Login updatedAdmin = new Login();
        updatedAdmin.setUserName(userName);

        when(customerRepository.findByUserName(userName)).thenReturn(Optional.empty());

        assertThrows(AdminNotFoundException.class, () -> adminService.updateAdmin(userName, updatedAdmin));
    }

    // Test cases for other methods...

    @Test
    public void testGetCartById() {
        int cartId = 1;
        CartDto cartDto = new CartDto();
        ResponseEntity<CartDto> responseEntity = ResponseEntity.ok(cartDto);

        when(restTemplate.getForEntity("http://localhost:3333/cart/" + cartId, CartDto.class)).thenReturn(responseEntity);

        CartDto result = adminService.getCartById(cartId);

        assertEquals(cartDto, result);
    }

    @Test
    public void testGetAllCarts() {
        CartDto[] cartDtos = {new CartDto(), new CartDto()};
        ResponseEntity<CartDto[]> responseEntity = ResponseEntity.ok(cartDtos);

        when(restTemplate.getForEntity("http://localhost:3333/cart/getallcarts", CartDto[].class)).thenReturn(responseEntity);

        List<CartDto> result = adminService.getAllCarts();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetOrderById() {
        int orderId = 1;
        OrderDto orderDto = new OrderDto();
        ResponseEntity<OrderDto> responseEntity = ResponseEntity.ok(orderDto);

        when(restTemplate.getForEntity("http://localhost:8090/order/getorderbyid/" + orderId, OrderDto.class)).thenReturn(responseEntity);

        OrderDto result = adminService.getOrderById(orderId);

        assertEquals(orderDto, result);
    }

    @Test
    public void testGetAllOrders() {
        OrderDto[] orderDtos = {new OrderDto(), new OrderDto()};
        ResponseEntity<OrderDto[]> responseEntity = ResponseEntity.ok(orderDtos);

        when(restTemplate.getForEntity("http://localhost:8090/order/allorders", OrderDto[].class)).thenReturn(responseEntity);

        List<OrderDto> result = adminService.getAllOrders();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetPaymentDetails() {
        int transactionId = 1;
        PaymentDto paymentDto = new PaymentDto();
        ResponseEntity<PaymentDto> responseEntity = ResponseEntity.ok(paymentDto);

        when(restTemplate.getForEntity("http://localhost:8085/payment/getBytId/" + transactionId, PaymentDto.class)).thenReturn(responseEntity);

        PaymentDto result = adminService.getPaymentDetails(transactionId);

        assertEquals(paymentDto, result);
    }
}
