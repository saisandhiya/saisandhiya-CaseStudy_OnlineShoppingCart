package com.naidu.payment.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="payment")
public class Payment {
	@Id
	private int transactionId;
	private int cartId;
	private String userName;
	private double amount;
	private String transactionStatus;
	private String paymentMode;
	
}
