package com.naidu.payment;

 

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.naidu.payment.controller.PaymentInterface;
import com.naidu.payment.entity.Cart;
import com.naidu.payment.entity.Payment;
import com.naidu.payment.exception.PaymentFailException;
import com.naidu.payment.exception.PaymentNotFoundWithIdException;
import com.naidu.payment.repository.PaymentRepository;
import com.naidu.payment.service.PaymentServiceImp;

 

class PaymentServiceTests {

 

	@Mock
	private PaymentRepository paymentRepository;

 

	@Mock
	private PaymentInterface paymentInterface;

 

	@InjectMocks
	private PaymentServiceImp paymentService;

 

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

 

//	 @Test
//	    void testDoPaymentValidCart() throws PaymentFailException {
//	        int cartId = 1;
//	        String userName = "John Doe";
//	        String paymentMode = "Credit Card";
//
//	        // Mock the response from paymentInterface.getCartById
//	        Cart cart = new Cart();
//	        cart.setCartId(cartId);
//	        cart.setTotalPrice(100.0); // Example price
//	        ResponseEntity<Cart> cartResponseEntity = ResponseEntity.ok(cart);
//	        when(paymentInterface.getCartById(cartId)).thenReturn(cartResponseEntity);
//
//	        // Call the service method
//	        Payment payment = paymentService.doPayment(cartId, userName, paymentMode);
//
//	        // Verify the result
//	        assertNotNull(payment);
//	        assertEquals(cartId, payment.getCartId());
//	        assertEquals(userName, payment.getUserName());
//	        assertEquals(paymentMode, payment.getPaymentMode());
//	    }

 

	
	@Test
	void testDoPaymentInvalidCart() {
		int cartId = 1;
		String userName = "John Doe";
		String paymentMode = "Credit Card";

 

		// Mock an invalid cart
		Cart cart = new Cart();
		cart.setCartId(0); // Invalid cart ID
		ResponseEntity<Cart> cartResponseEntity = ResponseEntity.ok(cart);
		when(paymentInterface.getCartById(cartId)).thenReturn(cartResponseEntity);

 

		// Call the service method and expect PaymentFailException
		assertThrows(PaymentFailException.class, () -> {
			paymentService.doPayment(cartId, userName, paymentMode);
		});
	}

 

	@Test
	void testGetPayment() throws PaymentNotFoundWithIdException {
		int transactionId = 123;
		when(paymentRepository.findByTransactionId(transactionId)).thenReturn(new Payment());

 

		Payment payment = paymentService.getPayment(transactionId);

 

		assertNotNull(payment);
	}

 

	@Test
	void testGetAllPayments() {
		// Mocking the response from the repository
		List<Payment> paymentList = new ArrayList<>();
		when(paymentRepository.findAll()).thenReturn(paymentList);

 

		List<Payment> result = paymentService.getAllPayments();

 

		assertNotNull(result);
	}
}