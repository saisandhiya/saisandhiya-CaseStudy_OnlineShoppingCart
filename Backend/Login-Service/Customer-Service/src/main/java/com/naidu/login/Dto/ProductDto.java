package com.naidu.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
	private int productId;
	private String productType;
	private String productName;
	private String category;
	private String rating;
	private String review;
	private double price;
	private String description;
	private String specification;

	

	

}
