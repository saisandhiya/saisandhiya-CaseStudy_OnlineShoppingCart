package com.OnlineShoppingCart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShoppingCart.Entity.Product;
import com.OnlineShoppingCart.Exceptions.ProductNotFoundException;
import com.OnlineShoppingCart.ServiceImpl.ProductServiceImpl;

import jakarta.validation.Valid;

import java.util.List;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	ProductServiceImpl productServiceimpl;
	
	
@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product)  {
		logger.info("Sending request to the product");
		Product p1=productServiceimpl.addProduct(product);
		logger.info("added");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product> >getAllProduct() {
		logger.info("Sending request to the product");
		List<Product> p1=productServiceimpl.getAllProduct();
		logger.info("get all products");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
		
	}
	@GetMapping("/getproduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id)throws ProductNotFoundException{
		
			logger.info("Sending request to the product");
			Product p1=productServiceimpl.getProductById(id);
			logger.info("get product by id");
			return new ResponseEntity<>(p1,HttpStatus.OK);
		
	}
	@GetMapping("/getproductByname/{productName}")
	public ResponseEntity< List<Product> >getProductByName(@PathVariable String productName){
		logger.info("Sending request to the product");
		List<Product> p1=productServiceimpl.getProductByName(productName);
		logger.info("get product by name");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
		
	}
	@PutMapping("/updateMapping/{productId}")
	public ResponseEntity<Product> updateProducts(@PathVariable int productId,@RequestBody Product product) {
		logger.info("Sending request to the product");
		Product p1=productServiceimpl.updateProducts(productId, product);
		logger.info("get product by name");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/deleteMapping/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable int productId) {
		logger.info("Sending request to the product");
		String p1=productServiceimpl.deleteProductById(productId);
		logger.info("get product by name");
		return new ResponseEntity<>(p1,HttpStatus.OK);
		
	}
	@GetMapping("/getproductcategory/{category}")
	public ResponseEntity <List<Product> >getProductByCategory(@PathVariable String category){
		logger.info("Sending request to the product");
		List<Product> p1=productServiceimpl.getProductByCategory(category);
		logger.info("get product by name");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
		
		
	}
	@GetMapping("/getproductbytype/{productType}")
	public ResponseEntity<List<Product>> getProductByType(@PathVariable String productType){
		logger.info("Sending request to the product");
		List<Product> p1=productServiceimpl.getProductByType(productType);
		logger.info("get product by name");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllProductbyName/{productName}")
	public ResponseEntity<List<Product>> getAllProductByName(@PathVariable String productName){
		logger.info("Sending request to the product");
		List<Product> p1=productServiceimpl.getAllProductsByName(productName);
		logger.info("get product by name");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
	}
	

}
