package com.shoppingcart.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingcart.order.controller.PaymentInterface;
import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.entity.Cart;
import com.shoppingcart.order.entity.Orders;
import com.shoppingcart.order.exception.AddressNotFoundException;
import com.shoppingcart.order.exception.OrdersNotFoundException;
import com.shoppingcart.order.repository.AddressRepository;
import com.shoppingcart.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderrepository;

	@Autowired
	AddressRepository addressrepository;
	
	@Autowired
	PaymentInterface paymentInterface;

	public List<Orders> getAllOrders() throws OrdersNotFoundException {
		
		return orderrepository.findAll();

	}

	public List<Address> getAllAddressByCustomerName(String name) throws AddressNotFoundException {
		List<Address> addresses = addressrepository.findAllByCustomerName(name);
		return addresses;
	}

	public Orders getOrderByOrderId(int id) throws OrdersNotFoundException {
		Optional<Orders> findById = orderrepository.findByOrderId(id);
		if (!findById.isEmpty()) {
			return findById.get();
		} else {
			throw new OrdersNotFoundException(id + " Does Not Exists");
		}
	}

//	public List<Address> getOrderByCustomerId(Integer id) throws AddressNotFoundException {
//		List<Address> findById= addressrepository.findByCustomerId(id);
//		if (!findById.isEmpty()) {
//			return addressrepository.findByCustomerId(id);
//		} else {
//			throw new AddressNotFoundException(id + " Does Not Exists");
//		}
//
//	}

//	public List<Orders> getOrdersByAddress(int id) throws OrdersNotFoundException {
//		List<Orders> findByCustomerId = orderrepository.findByCustomerId(id);
//		if (!findByCustomerId.isEmpty()) {
//			return orderrepository.findByCustomerId(id);
//		} else {
//			throw new OrdersNotFoundException(id + " Does Not Exists");
//		}
//	}

	@Override
	public Address storeAddress(Address address) {
		Optional<Address> findByCustomerId = addressrepository.findById(address.getCustomerId());
		List<Address> addresses = addressrepository.findAll();
		if (findByCustomerId.isPresent() == false) {
			return addressrepository.save(address);
		} else {
			throw new AddressNotFoundException(address.getCustomerName() + " Already exists");
		}
	}

	@Override
	public Orders changeOrderStatus(String status, int id) {
		Optional<Orders> findByOrderId = orderrepository.findByOrderId(id);
		if (!findByOrderId.isEmpty()) {
			Orders order = orderrepository.findByOrderId(id).orElseThrow();
			order.setOrderStatus(status);
			return order;
		} else {
			throw new OrdersNotFoundException("Invalid Id status cannot be changed");
		}

	}

	@Override
	public String deleteOrder(int id) throws OrdersNotFoundException {
		Optional<Orders> findById = orderrepository.findById(id);
		if (findById.isPresent()) {
			orderrepository.deleteById(id);
			return "Deleted Sucessfully";

		} else {
			throw new OrdersNotFoundException(id + " Already been deleted or not been present");
		}

	}

	@Override
	public Orders placeorders(Orders order, int cartId) {
		Optional<Orders> findById = orderrepository.findById(order.getOrderId());
		ResponseEntity<Cart> cart = paymentInterface.getCartById(cartId);
		if (findById.isEmpty()) {
			if(cart.getBody() != null) {
				order.setOrderId(orderrepository.findAll().size()+1);
				order.setCart(cart.getBody());
//				order.setCustomerName(order.getAddress().getFullName());
				order.setAmountPaid(cart.getBody().getTotalPrice());
				order.setOrderStatus("order placed");
				order.setOrderDate(LocalDate.now());
//				paymentInterface.deleteCart(cartId);
				return orderrepository.insert(order);
			}else {
				throw new OrdersNotFoundException("Invalid CartId");
			}
			
		} else {
			throw new OrdersNotFoundException("Order is already placed on this id");
		}
	}
//	
	public List<Orders> getAllOrdersByCustomerName(String name){
		return orderrepository.findAllByCustomerName(name);
	}
	
	
	public List<Address> getAllAddress(){
		return addressrepository.findAll();
	}
	
	
	public String deleteAddress(int id) {
		Address address = addressrepository.findByCustomerId(id);
		if(address != null) {
			addressrepository.deleteById(id);
			return "Address Deleted";
		}
		else {
			throw new OrdersNotFoundException("address not found");
		}
		
	}
	
	


}
