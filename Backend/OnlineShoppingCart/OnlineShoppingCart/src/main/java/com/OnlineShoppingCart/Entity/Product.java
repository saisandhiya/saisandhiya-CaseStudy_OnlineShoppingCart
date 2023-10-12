package com.OnlineShoppingCart.Entity;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.PositiveOrZero;

@Document(collection = "product")
public class Product {
	    @Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int productId;

	    @NotBlank(message = "Product type is required")
	    private String productType;

	    @NotBlank(message = "Product name is required")
	    private String productName;

	    @NotBlank(message = "Category is required")
	    private String category;

	    @Pattern(regexp = "[1-5]", message = "Rating should be between 1 and 5")
	    private String rating;

	    @NotBlank(message = "Review is required")
	    private String review;

	    @NotBlank(message = "Image is required")
	    private String image;

	    @PositiveOrZero(message = "Price should be a positive value")
	    private double price;

	    @NotBlank(message = "Description is required")
	    private String description;

	    @NotBlank(message = "Specification is required")
	    private String specification;


	public Product() {

	}

	public Product(int productId, String productType, String productName, String category, String rating, String review,
			String image, double price, String description, String specification) {
		super();
		this.setProductId(productId);
		this.productType = productType;
		this.productName = productName;
		this.category = category;
		this.rating = rating;
		this.review = review;
		this.image = image;
		this.price = price;
		this.description = description;
		this.specification = specification;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
