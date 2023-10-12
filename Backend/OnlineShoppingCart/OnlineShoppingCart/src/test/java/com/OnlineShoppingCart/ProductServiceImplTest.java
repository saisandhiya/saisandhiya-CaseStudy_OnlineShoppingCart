package com.OnlineShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.OnlineShoppingCart.Entity.Product;
import com.OnlineShoppingCart.Repository.ProductRepository;
import com.OnlineShoppingCart.ServiceImpl.ProductServiceImpl;

@SpringBootTest
 class ProductServiceImplTest {
	@Autowired
	private ProductServiceImpl productSerImpl;
	@MockBean
	private ProductRepository productrepo;

	
	/*
	 * @Test public void addProduct_test() {
	 * 
	 * Product p = new Product(); p.setProductId(3); p.setProductName("iphone");
	 * p.setProductType("samsung"); p.setCategory("oneplus");
	 * p.setDescription("gudd"); p.setPrice(100); p.setRating("5");
	 * p.setImage("circle"); p.setReview("good"); p.setSpecification("imax");
	 * 
	 * 
	 * when(productrepo.existsById(3)).thenReturn(false);
	 * when(productrepo.save(p)).thenReturn(p); assertEquals(p,
	 * productSerImpl.addProduct(p)); }
	 */
	
	@Test
	public void getallproduct() {
		List<Product> products = new ArrayList<>();
		Product p = new Product();

		p.setProductId(3);
		p.setProductName("iphone");
		p.setProductType("samsung");
		p.setCategory("oneplus");
		p.setDescription("gudd");
		p.setPrice(100);
		p.setRating("5");
		p.setImage("circle");
		p.setReview("good");
		p.setSpecification("imax");

		products.add(p);

		when(productrepo.findAll()).thenReturn(products);
		assertEquals(1, productSerImpl.getAllProduct().size());
	}
	
	@Test
	public void ProductById() {
		//List<Product> products = new ArrayList<>();

		Product p = new Product();

		p.setProductId(3);
		p.setProductName("iphone");
		p.setProductType("samsung");
		p.setCategory("oneplus");
		p.setDescription("gudd");
		p.setPrice(100);
		p.setRating("5");
		p.setImage("circle");
		p.setReview("good");
		p.setSpecification("imax");
		//products.add(p);


		Optional<Product> byproductid = Optional.of(p);
		when(productrepo.findById(3)).thenReturn(byproductid);
		assertEquals(p, productSerImpl.getProductById(3));

	}


//	@Test
//	public void addProduct_test() {
//
//		Product p = new Product();
//		p.setProductId(3);
//		p.setProductName("iphone");
//		p.setProductType("samsung");
//		p.setCategory("oneplus");
//		p.setDescription("gudd");
//		p.setPrice(100);
//		p.setRating("5");
//		p.setImage("circle");
//		p.setReview("good");
//		p.setSpecification("imax");
//
//		
//
//		when(productrepo.insert(p)).thenReturn(p);
//		assertEquals(p, productSerImpl.addProduct(p));
//
//	}
	/*
	 * @Test public void updateProduct_test() {
	 * 
	 * Product p = new Product();
	 * 
	 * p.setProductId(3); p.setProductName("iphone"); p.setProductType("samsung");
	 * p.setCategory("oneplus"); p.setDescription("gudd"); p.setPrice(100);
	 * p.setRating("5"); p.setImage("circle"); p.setReview("good");
	 * p.setSpecification("imax");
	 * 
	 * Product p1 = new Product();
	 * 
	 * p1.setProductId(3); p1.setProductName("iphone15");
	 * p1.setProductType("Phone"); p1.setCategory("mobile");
	 * p1.setDescription("gudd"); p1.setPrice(100); p1.setRating("5");
	 * p1.setImage("circle"); p1.setReview("good"); p1.setSpecification("imax");
	 * 
	 * 
	 * 
	 * when(productrepo.existsById(3)).thenReturn(true);
	 * when(productrepo.findById(3)).thenReturn(Optional.of(p));
	 * when(productrepo.save(p1)).thenReturn(p1); assertEquals(p1,
	 * productSerImpl.updateProducts(3, p1)); }
	 */
	
	
	
	
	
	@Test
	public void getProductByName_test() {
		
		Product p = new Product();

		p.setProductId(3);
		p.setProductName("iphone");
		p.setProductType("samsung");
		p.setCategory("oneplus");
		p.setDescription("gudd");
		p.setPrice(100);
		p.setRating("5");
		p.setImage("circle");
		p.setReview("good");
		p.setSpecification("imax");

		List<Product> byproductname = List.of(p);
		when(productrepo.findByProductName("iphone")).thenReturn(byproductname);
		assertEquals(byproductname, productSerImpl.getProductByName("iphone"));
		
	}
	
	
	
	
	

	@Test
	public void getProductByCategory() {
		Product p = new Product();

		p.setProductId(3);
		p.setProductName("iphone");
		p.setProductType("samsung");
		p.setCategory("oneplus");
		p.setDescription("gudd");
		p.setPrice(100);
		p.setRating("5");
		p.setImage("circle");
		p.setReview("good");
		p.setSpecification("imax");

		List<Product> byproductcategory = List.of(p);
		when(productrepo.findByCategory("oneplus")).thenReturn(byproductcategory);
		assertEquals(byproductcategory, productSerImpl.getProductByCategory("oneplus"));
		
	}
	
	
	@Test
	public void getProductByType_test() {
		
		Product p = new Product();
		p.setProductId(3);
		p.setProductName("iphone");
		p.setProductType("samsung");
		p.setCategory("oneplus");
		p.setDescription("gudd");
		p.setPrice(100);
		p.setRating("5");
		p.setImage("circle");
		p.setReview("good");
		p.setSpecification("imax");
		
		List<Product> byproducttype = List.of(p);
		when(productrepo.findByProductType("samsung")).thenReturn(byproducttype);
		assertEquals(byproducttype, productSerImpl.getProductByType("samsung"));
	}
	
}

