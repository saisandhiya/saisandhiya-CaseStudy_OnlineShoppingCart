package com.naidu.payment.service;

import java.util.List;

import com.naidu.payment.entity.Payment;
import com.naidu.payment.entity.PaymentType;
import com.naidu.payment.exception.PaymentFailException;
import com.naidu.payment.exception.PaymentNotFoundWithIdException;


public interface PaymentService {
	
	Payment  doPayment(int cartId,String userName,String paymentMode) throws PaymentFailException;
	Payment getPayment(int transactionId) throws PaymentNotFoundWithIdException;
	//Payment updatePayment(int transactionId,Payment payment) throws PaymentNotFoundWithIdException;
	
	List<Payment> getAllPayments();

}
