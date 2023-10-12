package com.naidu.CartService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.naidu.CartService.entity.Cart;
import com.naidu.CartService.entity.Items;
import com.naidu.CartService.entity.Product;
import com.naidu.CartService.exception.CartAlreadyExistsException;
import com.naidu.CartService.exception.CartNotFoundException;
import com.naidu.CartService.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	CartRepository cartRepository;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Cart getCartById(int cartId) throws CartNotFoundException {
		if (cartRepository.existsById(cartId)) {
			logger.info("Cart details of id exists " + cartId);
			return cartRepository.findById(cartId);
		} else {
			logger.warn("CART NOT FOUND WITH ID " + cartId);
			throw new CartNotFoundException("CART NOT FOUND WITH ID " + cartId);
		}
	}

	@Override
	public Cart updateCart(int cartId, Cart cart) throws CartNotFoundException {
		if (cartRepository.existsById(cartId)) {
			Cart updatedCart = cartRepository.findById(cartId);
			updatedCart.setCartId(cart.getCartId());
			updatedCart.setItems(cart.getItems());
			updatedCart.setTotalPrice(cart.getTotalPrice());
			cartRepository.save(updatedCart);
			return updatedCart;

		} else {
			logger.warn("NO CART FOUND WITH ID " + cartId);
			throw new CartNotFoundException("NO CART FOUND WITH ID " + cartId);
		}

	}

	@Override
	public List<Cart> getallcarts() throws CartNotFoundException {
		List<Cart> carts = cartRepository.findAll();
		if (carts.isEmpty()) {
			throw new CartNotFoundException("NO CARTS EXISTS");
		} else {
			return carts;
		}
	}

	@Override
	public double cartTotal(Cart cart) {

		return cart.getTotalPrice();
	}

	@Override
	public Cart addCart(Cart cart) throws CartAlreadyExistsException {

		return cartRepository.save(cart);

	}

	@Override
	public String deleteCartById(int cartId) {
		cartRepository.deleteById(cartId);
		return "Deleted Successfully";

	}

	@Override
	public Cart addProductToCart(int cartId, int productId) {
		// Fetch the product details from another service using RestTemplate
		Product product = restTemplate.getForObject("http://localhost:3334/product/getproduct/" + productId, Product.class);
		logger.info("Fetched product details: " + product);

		// Check if the cart exists
		Cart cart;
		if (cartRepository.existsById(cartId)) {
			cart = cartRepository.findById(cartId);

			// Check if the product is already in the cart
			boolean productExistsInCart = cart.getItems().stream().anyMatch(item -> item.getProductId() == productId);

			if (productExistsInCart) {
				// If the product exists in the cart, increase its quantity
				for (Items item : cart.getItems()) {
					if (item.getProductId() == productId) {
						logger.info("Product already exists in the cart. Increasing quantity.");
						item.setQuantity(item.getQuantity() + 1);
						break;
					}
				}
			} else {
				// If the product is not in the cart, add it as a new item
				logger.info("Product not in the cart. Adding as a new item.");
				Items newItem = new Items();
				newItem.setProductId(productId);
				newItem.setPrice(product.getPrice());
				newItem.setProductName(product.getProductName());
				newItem.setQuantity(1);
				newItem.setImage(product.getImage());
				cart.getItems().add(newItem);
			}
		} else {
			// If the cart doesn't exist, create a new cart and add the product as an item
			logger.info("Cart doesn't exist. Creating a new cart and adding the product.");
			cart = new Cart();
			cart.setCartId(cartId);

			Items newItem = new Items();
			newItem.setProductId(productId);
			newItem.setPrice(product.getPrice());
			newItem.setProductName(product.getProductName());
			newItem.setQuantity(1);
			newItem.setImage(product.getImage());

			List<Items> itemList = new ArrayList<>();
			itemList.add(newItem);
			cart.setItems(itemList);
		}

		// Calculate total price of the cart
		double totalPrice = calculateTotalPrice(cart);
		cart.setTotalPrice(totalPrice);

		// Save or update the cart
		return cartRepository.save(cart);
	}

	@Override
	public Cart deleteCartItem(int cartId, int productId) throws CartNotFoundException {
		Product product = restTemplate.getForObject("http://localhost:3334/product/getproduct/" + productId, Product.class);
		Cart cart = getCartById(cartId);

		List<Items> itemsList = cart.getItems();
		Iterator<Items> iterator = itemsList.iterator();

		while (iterator.hasNext()) {
			Items item = iterator.next();
			if (item.getProductId() == productId) {
				iterator.remove();
				break;
			}
		}

		double totalPrice = calculateTotalPrice(cart);
		cart.setTotalPrice(totalPrice);

		return updateCart(cartId, cart);
	}
	
	
	
	 @Override
	    public Cart decreaseItem(int productId, int cartId) throws CartNotFoundException {
	        logger.info("Attempting to decrease quantity for product with ID {} in cart with ID {}", productId, cartId);

	        Cart cart = getCartById(cartId);
	        List<Items> itemsList = cart.getItems();

	        for (Items item : itemsList) {
	            if (item.getProductId() == productId) {
	                logger.info("Product found in cart. Decreasing quantity by 1 for product with ID {}", productId);
	                int currentQuantity = item.getQuantity();
	                if(currentQuantity > 0) {
	                    item.setQuantity(currentQuantity - 1);
	                    if(item.getQuantity() == 0) {
		                	itemsList.remove(item);
		                	break;
		                }
	                }
	            }
	        }

	        // Recalculate and update the total price
	        double totalPrice = calculateTotalPrice(cart);
	        cart.setTotalPrice(totalPrice);
	        cart.setItems(itemsList);

	        // Update the cart
	        return updateCart(cartId, cart);
	    }

	// Helper method to calculate the total price of the cart
	private double calculateTotalPrice(Cart cart) {
		double totalPrice = 0;
		for (Items item : cart.getItems()) {
			totalPrice += item.getPrice() * item.getQuantity();
		}
		return totalPrice;
	}

}
