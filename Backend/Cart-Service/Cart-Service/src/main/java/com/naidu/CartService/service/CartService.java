package com.naidu.CartService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.naidu.CartService.entity.Cart;
import com.naidu.CartService.exception.CartNotFoundException;

@Service
public interface CartService {
	public Cart getCartById(int cartId);

	public Cart updateCart(int cartId, Cart cart);

	public List<Cart> getallcarts();

	public double cartTotal(Cart cart);

	public Cart addCart(Cart cart);

	public String deleteCartById(int cartId);
	
	public Cart addProductToCart(int cartId,int productId);

	Cart deleteCartItem(int cartId, int productId) throws CartNotFoundException;

	Cart decreaseItem(int productId, int cartId) throws CartNotFoundException;

}
