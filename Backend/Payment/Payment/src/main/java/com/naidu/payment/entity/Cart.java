package com.naidu.payment.entity;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	private int cartId;
	private List<Itemss> items;
	private double totalPrice;

}
