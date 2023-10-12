package com.naidu.login.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private int orderId;
	private LocalDate orderDate;

	private int customerId;

	private double amountPaid;

	private String modeOfPayment;

	private String orderStatus;

	private int quantity;

	private AddressDto address;
	private CartOrderDto cart;
	private int productId;

}
