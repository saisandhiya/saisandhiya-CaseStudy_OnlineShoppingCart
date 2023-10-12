package com.OnlineShoppingCart.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlineShoppingCart.Entity.Product;

@Service
public interface ProductService {

	public Product addProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProductById(int id);

	public List<Product> getProductByName(String productName);

	public Product updateProducts(int id, Product product);

	public String deleteProductById(int id);

	public List<Product> getProductByCategory(String category);

	public List<Product> getProductByType(String productType);

}
