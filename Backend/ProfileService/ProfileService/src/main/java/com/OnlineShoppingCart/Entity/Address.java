package com.OnlineShoppingCart.Entity;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="address")
public class Address {
	@Id

	private int houseNumber;
	private String streetName;
	private String colonyName;
	private String city;
	private String state;
	@NotNull
	private int pincode;

//	public Address() {
//
//}
//	public Address(int houseNumber, String streetName, String colonyName, String city, String state, int pincode) {
//		super();
//		this.houseNumber = houseNumber;
//		this.streetName = streetName;
//		this.colonyName = colonyName;
//		this.city = city;
//		this.state = state;
//		this.pincode = pincode;
//	}
//	public int getHouseNumber() {
//		return houseNumber;
//	}
//	public void setHouseNumber(int houseNumber) {
//		this.houseNumber = houseNumber;
//	}
//	public String getStreetName() {
//		return streetName;
//	}
//	public void setStreetName(String streetName) {
//		this.streetName = streetName;
//	}
//	public String getColonyName() {
//		return colonyName;
//	}
//	public void setColonyName(String colonyName) {
//		this.colonyName = colonyName;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
//	public int getPincode() {
//		return pincode;
//	}
//	public void setPincode(int pincode) {
//		this.pincode = pincode;
//	}
}
	
