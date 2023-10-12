package com.naidu.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itemss {
	private int productId;

	private String productName;

	private double price;

	private int quantity;
	private String image;

}
