package com.shoppingcart.order.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "order")
public class Orders {

	@Id
//	@Positive(message="OrderId must be positive")
	private int orderId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	private String customerName;
	
	@NotNull
	private double amountPaid;
	
	@NotEmpty
	private String modeOfPayment;
	
	private String orderStatus;
	
	private int quantity;
	
	private Address address;
	private Cart cart;
	private int productId;

}
