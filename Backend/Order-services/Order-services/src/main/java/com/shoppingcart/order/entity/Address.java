package com.shoppingcart.order.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "orderaddress")
public class Address {

	@Id
//	@Positive(message="customerId must be positive")
	private int customerId;
	
	
	private String customerName;
	
	@NotEmpty(message="Name should not be empty")
	private String fullName;
	
	@Size (min=10,max=10,message="mobile number must be 10")
	private String mobileNumber;
	
	private int flatNumber;
	private String city;
	
	
	private int pincode;
	private String state;

}
