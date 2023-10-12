package com.naidu.CartService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

import com.naidu.CartService.controller.CartController;

import com.naidu.CartService.entity.Cart;

import com.naidu.CartService.repository.CartRepository;

import com.naidu.CartService.service.CartServiceImpl;

// Import statements for test dependencies

@ExtendWith(MockitoExtension.class)

class CartControllerTest {

	@Mock

	private CartServiceImpl cartService;

	@Mock

	private CartRepository cartRepository;

	@Mock

	private RestTemplate restTemplate;

	@InjectMocks

	private CartController cartController;

	@BeforeEach

	void setUp() {

		// Initialize mock behavior here

		MockitoAnnotations.openMocks(this);

	}

	@Test

	void testGetAllCarts_Successful() {

		// Arrange

		List<Cart> cartList = new ArrayList<>();

		when(cartService.getallcarts()).thenReturn(cartList);

		// Act

		ResponseEntity<List<Cart>> response = cartController.getAllCarts();

		// Assert

		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertEquals(cartList, response.getBody());

	}

//	@Test

//	void testAddProductToCart_Successful() {

//		// Arrange

//		int cartId = 11;

//		int productId = 13;

//		Cart updatedCart = new Cart(); // Provide a valid Cart object

//		updatedCart.setCartId(cartId);

//		updatedCart.setTotalPrice(productId);

//		when(cartService.addProductToCart(cartId, productId)).thenReturn(updatedCart);

//

//		// Act

//		ResponseEntity<Cart> response = cartController.addProductToCart(cartId, productId);

//

//		// Assert

//		assertEquals(HttpStatus.CREATED, response.getStatusCode());

//		assertNotNull(response.getBody());

//		assertEquals(updatedCart, response.getBody());

//	}

	@Test

	void testGetCartById_Successful() {

		// Arrange

		int cartId = 1;

		Cart cart = new Cart(); // Provide a valid Cart object

		when(cartService.getCartById(cartId)).thenReturn(cart);

		// Act

		ResponseEntity<Cart> response = cartController.getCartById(cartId);

		// Assert

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		assertEquals(cart, response.getBody());

	}

	@Test

	void testDeleteCartItem_Successful() {

		// Arrange

		int cartId = 1;

		int productId = 2;

		Cart updatedCart = new Cart(); // Provide a valid Cart object

		when(cartService.deleteCartItem(cartId, productId)).thenReturn(updatedCart);

		// Act

		ResponseEntity<Cart> response = cartController.deleteCartItem(cartId, productId);

		// Assert

		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertEquals(updatedCart, response.getBody());

	}

//@Test

//	void testDecreaseItem_Successful() {

//		// Arrange

//		int cartId = 1;

//		int productId = 2;

//		Cart cart = new Cart(); // Provide a valid Cart object

//		when(cartService.decreaseItem(productId, cartId)).thenReturn(cart);

//

//		// Act

//		Cart response = cartController.decreaseItem(productId, cartId);

//

//		// Assert (You can assert individual fields of the Cart object)

//		assertNotNull(response);

//	}

	@Test

	void testDeleteCart_Successful() {

		// Arrange

		int cartId = 1;

		// Act

		cartController.deleteCart(cartId);

		// Assert (You can use verify to check if cartServiceImpl.deleteCartById is

		// called)

		verify(cartService).deleteCartById(cartId);

	}

//

//	@Test

//	void testDeleteProductById_Successful() {

//		// Arrange

//		int cartId = 1;

//		when(cartService.deleteCartById(cartId)).thenReturn("Cart deleted successfully");

//

//		// Act

//		ResponseEntity<Cart> response = cartController.getCartById(cartId);

//

//		// Assert

//		assertEquals(HttpStatus.CREATED, response.getStatusCode());

//		assertEquals("Cart deleted successfully", response.getBody());

//	}

}