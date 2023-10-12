package com.naidu.payment;

 

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

import com.naidu.payment.controller.PaymentController;
import com.naidu.payment.entity.Payment;
import com.naidu.payment.exception.PaymentNotFoundWithIdException;
import com.naidu.payment.service.PaymentService;

 

class PaymentControllerTests {

 

    @Mock
    private PaymentService paymentService;

 

    @InjectMocks
    private PaymentController paymentController;

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

 

    @Test
    void testDoPayment() throws Exception {
        // Mock data
        int cartId = 1;
        String userName = "John Doe";
        String paymentMode = "Credit Card";
        Payment mockPayment = new Payment();

 

        // Mock service behavior
        when(paymentService.doPayment(cartId, userName, paymentMode)).thenReturn(mockPayment);

 

        // Call the API
        Payment result = paymentController.doPayment(cartId, userName, paymentMode);

 

        // Check the result
        assertNotNull(result);
        assertEquals(mockPayment, result);
    }

 

    @Test
    void testGetPayment() throws PaymentNotFoundWithIdException {
        // Mock data
        int transactionId = 123;
        Payment mockPayment = new Payment();

 

        // Mock service behavior
        when(paymentService.getPayment(transactionId)).thenReturn(mockPayment);

 

        // Call the API
        ResponseEntity<Payment> responseEntity = paymentController.getPayment(transactionId);

 

        // Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPayment, responseEntity.getBody());
    }

 

    @Test
    void testGetAllPayments() {
        // Mock data
        List<Payment> mockPayments = new ArrayList<>();
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        mockPayments.add(payment1);
        mockPayments.add(payment2);

 

        // Mock service behavior
        when(paymentService.getAllPayments()).thenReturn(mockPayments);

 

        // Call the API
        ResponseEntity<List<Payment>> responseEntity = paymentController.getPayment();

 

        // Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPayments, responseEntity.getBody());
    }
}