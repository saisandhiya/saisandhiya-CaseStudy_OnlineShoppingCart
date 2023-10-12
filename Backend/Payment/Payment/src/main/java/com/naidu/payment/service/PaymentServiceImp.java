package com.naidu.payment.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.naidu.payment.controller.PaymentInterface;
import com.naidu.payment.entity.Cart;
import com.naidu.payment.entity.Payment;
import com.naidu.payment.entity.PaymentType;
import com.naidu.payment.exception.PaymentFailException;
import com.naidu.payment.exception.PaymentNotFoundWithIdException;
import com.naidu.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {

	@Autowired
	PaymentRepository repository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	PaymentInterface paymentInterface;
	@Override
	public Payment doPayment(int cartId, String userName,String paymentMode) throws PaymentFailException {
		boolean paymentDone = false;
		//ResponseEntity<Cart> res = restTemplate.getForEntity("http://localhost:3333/cart/" + cartId, Cart.class);
		ResponseEntity<Cart> res = paymentInterface.getCartById(cartId);
		
		Cart cart = res.getBody();
		if (cart.getCartId() == 0) {
			throw new PaymentFailException("Invalid cart id");
		} else {
			int cid = cart.getCartId();
			double price = cart.getTotalPrice();
			try {
				Payment payment = new Payment();
				Random random = new Random();

				// Generate a random number between 100 and 1000 (inclusive)
				int randomNumber = random.nextInt(901) + 100;
				System.out.println(randomNumber);
				String tractionId = "" + cid + randomNumber;
				int id = Integer.parseInt(tractionId);
				payment.setTransactionId(id);
				payment.setCartId(cid);
				
				payment.setUserName(userName);
				payment.setAmount(price);
				
				/*
				 * if (paymentMode.equals(PaymentType.CASHON_DELIVERY.toString())) {
				 * payment.setTransactionStatus("Payment Pending"); } else {
				 * payment.setTransactionStatus("Payment Successful"); }
				 */
				payment.setTransactionStatus("Payment Successful");
				payment.setPaymentMode(paymentMode);
				paymentDone = true;
				Payment save = repository.save(payment);
				paymentInterface.deleteCart(cartId);
				return save;

			} catch (Exception e) {
				// TODO: handle exception

				throw new PaymentFailException("Payment Failed of RS " + price);

			}
		}

	}

	@Override
	public Payment getPayment(int transactionId) throws PaymentNotFoundWithIdException {
		Payment pay = repository.findByTransactionId(transactionId);
		if(pay!= null) {
		//Payment payment= repository.findByTransactionId(transactionId);
		return pay;
		}else {
			throw new PaymentNotFoundWithIdException("Payment not found with transactionId "+transactionId);
		}
	}
	
	public List<Payment> getAllPayments(){
		return repository.findAll();
	}
	
	
//
//	@Override
//	public Payment updatePayment(int transactionId, Payment payment) throws PaymentNotFoundWithIdException {
//		// TODO Auto-generated method stub
//		if(repository.existsByTransactionId(transactionId)) {
//			repository.save(payment);
//			return payment;
//			}else {
//				throw new PaymentNotFoundWithIdException("Payment not found with transactionId "+transactionId);
//			}
////		repository.save(payment);
////		return payment;

}
