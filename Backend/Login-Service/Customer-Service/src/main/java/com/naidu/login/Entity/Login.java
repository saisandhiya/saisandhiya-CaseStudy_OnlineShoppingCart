package com.naidu.login.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	@Id
	//private int loginId;
	@NotEmpty(message = "User name is required")
	private String userName;

	@NotEmpty(message = "First name is required")
	private String firstName;

	@NotEmpty(message = "Last name is required")
	private String lastName;

	@NotEmpty(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must start with 6, 7, 8, or 9 and have a maximum of 10 digits")
	private String mobileNumber;

	@NotEmpty(message = "Email is required")
	@Email(message = "Please enter a valid email address")
	//@Column(unique = true)
	private String email;
	@NotBlank(message="Role should not be blank")
	private String role;

}
