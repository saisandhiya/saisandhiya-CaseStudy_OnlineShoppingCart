package com.naidu.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartOrderDto {
	private int cartid;
	private double totalPrice;
	private ProductOrderDto product;

}
