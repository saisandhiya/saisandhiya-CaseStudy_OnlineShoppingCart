package com.naidu.login.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private int customerId;

	private String fullName;

	private String mobileNumber;

	private int flatNumber;

	private String city;

	private int pincode;

	private String state;

}
