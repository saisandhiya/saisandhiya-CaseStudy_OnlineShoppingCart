package com.naidu.login.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

	private int cartId;
	private List<Items> items;
	private double totalPrice;

}
