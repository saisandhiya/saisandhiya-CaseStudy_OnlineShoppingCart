package com.OnlineShoppingCart.Entity;

import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userProfile")
public class UserProfile {
	@Id


	@Positive(message = "Profile Id must be Positive")
	private Integer profileId;
	@NotEmpty(message = "Name should not be empty")
	private String fullName;
	@Pattern(regexp = "^[a-zA-Z0-9_*]+@[a-zA-Z0-9_*]+\\.[a-zA-Z0-9_*]+$", message = "Please enter a valid email address")
	private String emailId;
	@NotNull(message = "not null")
	private Long mobileNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@NotNull
	private String gender;
	@NotNull
	private Address address;

//	public UserProfile() {
//
//	}
//
//	public UserProfile(Integer profileId, String fullName, String emailId, Long mobileNumber, LocalDate dateOfBirth,
//			String gender, Address address) {
//		super();
//		this.profileId = profileId;
//		this.fullName = fullName;
//		this.emailId = emailId;
//		this.mobileNumber = mobileNumber;
//		this.dateOfBirth = dateOfBirth;
//		this.gender = gender;
//		this.address = address;
//	}
//
//	public Integer getProfileId() {
//		return profileId;
//	}
//
//	public void setProfileId(Integer profileId) {
//		this.profileId = profileId;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getEmailId() {
//		return emailId;
//	}
//
//	public void setEmailId(String emailId) {
//		this.emailId = emailId;
//	}
//
//	public Long getMobileNumber() {
//		return mobileNumber;
//	}
//
//	public void setMobileNumber(Long mobileNumber) {
//		this.mobileNumber = mobileNumber;
//	}
//
//	public LocalDate getDateOfBirth() {
//		return dateOfBirth;
//	}
//
//	public void setDateOfBirth(LocalDate dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

}
