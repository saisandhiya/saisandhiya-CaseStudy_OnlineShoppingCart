package com.shoppingcart.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.order.entity.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, Integer>{

//	List<Address> findByCustomerId(Integer customerId);
	List<Address> findByFullName(String fullName);
	Address findByCustomerName(String customerName);
	List<Address> findAllByCustomerName(String name);
	Address findByCustomerId(int customerId);
}
