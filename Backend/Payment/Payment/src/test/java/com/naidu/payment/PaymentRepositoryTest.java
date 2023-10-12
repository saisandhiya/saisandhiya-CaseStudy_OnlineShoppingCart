package com.naidu.payment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.naidu.payment.entity.Payment;
import com.naidu.payment.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {

    @Mock
    private PaymentRepository paymentRepository;
    
    static List<Payment> pay = new ArrayList<>();
	
	static Payment p = new Payment();

    @BeforeEach
    void setUp() {
        // Initialize any setup or mocking needed before each test
    	p.setCartId(11);
    	p.setAmount(1200);
    	p.setTransactionId(123);
    	p.setTransactionStatus("paid");
    	p.setPaymentMode("credit card");
    	p.setUserName("sai");
    	pay.add(p);
    }
    
    @Test
	public void getall() {	

		when(paymentRepository.findAll()).thenReturn(pay);
		assertEquals(pay, paymentRepository.findAll());
	}

    @Test
    void testFindByTransactionId() {
        // Arrange
        int transactionId = 123;
        Payment payment = new Payment();
        payment.setTransactionId(transactionId);
        when(paymentRepository.findByTransactionId(transactionId)).thenReturn(payment);

        // Act
        Payment foundPayment = paymentRepository.findByTransactionId(transactionId);

        // Assert
        assertEquals(transactionId, foundPayment.getTransactionId());
    }

    @Test
    void testExistsByTransactionId() {
        // Arrange
        int transactionId = 456;
        when(paymentRepository.existsByTransactionId(transactionId)).thenReturn(true);

        // Act
        boolean exists = paymentRepository.existsByTransactionId(transactionId);

        // Assert
        assertTrue(exists);
    }

    @Test
    void testFindByTransactionId_NotFound() {
        // Arrange
        int transactionId = 789;
        when(paymentRepository.findByTransactionId(transactionId)).thenReturn(null);

        // Act
        Payment foundPayment = paymentRepository.findByTransactionId(transactionId);

        // Assert
        assertNull(foundPayment);
    }

    @Test
    void testExistsByTransactionId_NotFound() {
        // Arrange
        int transactionId = 999;
        when(paymentRepository.existsByTransactionId(transactionId)).thenReturn(false);

        // Act
        boolean exists = paymentRepository.existsByTransactionId(transactionId);

        // Assert
        assertFalse(exists);
    }
}
