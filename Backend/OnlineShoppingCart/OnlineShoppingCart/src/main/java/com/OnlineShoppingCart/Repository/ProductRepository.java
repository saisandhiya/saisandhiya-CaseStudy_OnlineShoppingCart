package com.OnlineShoppingCart.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShoppingCart.Entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

	Optional<Product> findByProductId(int productId);
	
	Optional<Product> findByProductId(String productId);
	
	List<Product> findByProductName(String productName);

	List<Product> findByCategory(String category);

	List<Product> findByProductType(String productType);

	List<Product> findAllByProductName(String name);

}
