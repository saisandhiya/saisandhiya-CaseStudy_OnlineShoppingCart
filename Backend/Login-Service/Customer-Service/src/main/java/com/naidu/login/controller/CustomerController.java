package com.naidu.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naidu.login.Dto.ProductDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.ProductNotFoundException;
import com.naidu.login.Service.ICustomerService;
import com.naidu.login.token.JwtUtility;

@RestController
@RequestMapping("/customer/access")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	@Autowired
	ICustomerService userService;

	@Autowired
	JwtUtility jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ICustomerService customerservice;

//	@PostMapping("/addCustomer")
//	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
//		Customer p1 = customerservice.addCustomer(customer);
//		return new ResponseEntity<>(p1, HttpStatus.CREATED);
//
//	}

//	@GetMapping("/getCustomerByid/{id}")
//	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
//	public ResponseEntity<Login> getCustomer(@PathVariable int id) {
//		Login p1 = customerservice.getCustomerByid(id);
//		return new ResponseEntity<>(p1, HttpStatus.CREATED);
//
//	}

	@DeleteMapping("/deleteCustomer/{userName}")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	public ResponseEntity<Login> deleteCustomer(@PathVariable String userName) {
		Login p1 = customerservice.deleteCustomer(userName);
		return new ResponseEntity<>(p1, HttpStatus.CREATED);

	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<Login> updateCustomer(@RequestBody Login customer) {
		Login c1 = customerservice.updateCustomer(customer);
		return new ResponseEntity<>(c1, HttpStatus.CREATED);

	}

//	@GetMapping("/getCustomers/")
//	// @PreAuthorize("hasAuthority('customer')")
//	public ResponseEntity<List<Login>> getCustomers() {
//		List<Login> p1 = customerservice.getCustomers();
//		return new ResponseEntity<>(p1, HttpStatus.CREATED);
//	}

	@GetMapping("/getEmail/{userName}")
	public ResponseEntity<String> getEmail(@PathVariable String userName) {
		String save = customerservice.getCustomerEmail(userName);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/getPassword/{userName}")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	public ResponseEntity<String> getPassword(@PathVariable String userName) {
		String save = customerservice.getCustomerPassword(userName);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/getCustomerByEmail/{email}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Login> getCustomerByEmail(@PathVariable String email) {
		Login save = customerservice.getCustomerByEmail(email);
		return new ResponseEntity<>(save, HttpStatus.CREATED);

	}

	@GetMapping("/viewProductById/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER','ROLE_ADMIN')")
	public ResponseEntity<ProductDto> viewProductById(@PathVariable int id) throws ProductNotFoundException {
		ProductDto save = customerservice.viewProductById(id);
		return new ResponseEntity<>(save, HttpStatus.CREATED);

	}

	@GetMapping("/viewAllProducts")
	@PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER','ROLE_ADMIN')")
	public ResponseEntity<List<ProductDto>> viewAllProducts() {
		List<ProductDto> p1 = customerservice.viewAllProducts();
		return new ResponseEntity<>(p1, HttpStatus.CREATED);

	}

	@PutMapping("/updateCustomerByEmail/{email}")
	public ResponseEntity<Login> updateCustomerByEmail(@PathVariable String email, @RequestBody Login customer) {
		Login c1 = customerservice.updateCustomerByEmail(email, customer);
		return new ResponseEntity<>(c1, HttpStatus.CREATED);

	}

}
