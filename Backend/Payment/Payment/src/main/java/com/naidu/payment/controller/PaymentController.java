package com.naidu.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naidu.payment.entity.Payment;
import com.naidu.payment.exception.PaymentNotFoundWithIdException;
import com.naidu.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin("http://localhost:4200")
public class PaymentController {

	@Autowired
	PaymentService service;

	@PostMapping("/doPayment/{cartId}/{userName}/{paymentMode}")
	public Payment doPayment(@PathVariable int cartId, @PathVariable String userName, @PathVariable String paymentMode)
			throws Exception {
		return service.doPayment(cartId, userName, paymentMode);
	}

	@GetMapping("/getBytId/{transactionId}")
	public ResponseEntity<Payment> getPayment(@PathVariable int transactionId) throws PaymentNotFoundWithIdException {
		Payment payment = service.getPayment(transactionId);
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}

	@GetMapping("/getAllPayments")
	public ResponseEntity<List<Payment>> getPayment() {
		List<Payment> payment = service.getAllPayments();
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}
	
}
