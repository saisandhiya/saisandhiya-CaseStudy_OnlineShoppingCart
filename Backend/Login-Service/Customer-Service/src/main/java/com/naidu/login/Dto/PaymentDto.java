package com.naidu.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

	private int transactionId;
	private int cartId;
	private String userName;
	private double amount;
	private String transactionStatus;
	private String paymentMode;

}
