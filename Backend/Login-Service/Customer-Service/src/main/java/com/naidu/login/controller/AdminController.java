package com.naidu.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naidu.login.Dto.CartDto;
import com.naidu.login.Dto.OrderDto;
import com.naidu.login.Dto.PaymentDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Service.IAdminService;
import com.naidu.login.Service.ICustomerService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	ICustomerService userService;
	
	@DeleteMapping("/deleteAdmin/{userName}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Login> deleteAdmin(@PathVariable String userName) {
		Login p1 = adminService.deleteAdmin(userName);
		return new ResponseEntity<>(p1, HttpStatus.CREATED);

	}

	@PutMapping("/updateAdmin/{userName}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Login> updateAdmin(@PathVariable String userName, @RequestBody Login admin) {
		Login c1 = adminService.updateAdmin(userName, admin);
		return new ResponseEntity<>(c1, HttpStatus.CREATED);

	}

	@GetMapping("/getCartById/{cartId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<CartDto> getCartById(@PathVariable int cartId) {
		CartDto save = adminService.getCartById(cartId);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/getAllCarts")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<CartDto>> viewAllCarts() {

		List<CartDto> save = adminService.getAllCarts();

		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/getOrderById/{orderId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable int orderId) {
		OrderDto save = adminService.getOrderById(orderId);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/viewAllOrders")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<OrderDto>> viewOrders() {
		List<OrderDto> save = adminService.getAllOrders();
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@GetMapping("/getPaymentDetailsById/{transactionId}")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	public ResponseEntity<PaymentDto> getPaymentDetails(@PathVariable int transactionId) {

		PaymentDto save = adminService.getPaymentDetails(transactionId);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCustomerByUserName/{username}")
	@PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER','ROLE_ADMIN')")
	public ResponseEntity<Login> getCustomer(@PathVariable String username) {
		Login p1 = userService.getCustomerByUserName(username);
		return new ResponseEntity<>(p1, HttpStatus.CREATED);

	}
	@GetMapping("/getCustomers/")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Login>> getCustomers() {
		List<Login> p1 = userService.getCustomers();
		return new ResponseEntity<>(p1, HttpStatus.CREATED);
	}

}
