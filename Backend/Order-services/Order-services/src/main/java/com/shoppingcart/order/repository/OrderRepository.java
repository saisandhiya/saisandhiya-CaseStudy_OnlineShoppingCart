package com.shoppingcart.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.order.entity.Orders;

@Repository
public interface OrderRepository extends MongoRepository<Orders, Integer>{

//	List<Orders> findByCustomerId(int customerId);
	Optional<Orders> findByOrderId(int orderId);

	List<Orders> findAllByCustomerName(String name);
	

	
	
}
