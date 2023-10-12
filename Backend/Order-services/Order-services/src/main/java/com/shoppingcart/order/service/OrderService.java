package com.shoppingcart.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.entity.Orders;

@Service
public interface OrderService {

	public List<Orders> getAllOrders();

	public List<Address> getAllAddressByCustomerName(String name);

	public Orders getOrderByOrderId(int id);

//	public List<Address> getOrderByCustomerId(Integer id);

//	public List<Orders> getOrdersByAddress(int id);

	public Address storeAddress(Address address);

	public Orders changeOrderStatus(String status, int id);

	public String deleteOrder(int id);

	public Orders placeorders(Orders order, int cartId);
	
//	public List<Orders> getAllOrdersByCustomerName(String name);
	
	public List<Address> getAllAddress();

}
