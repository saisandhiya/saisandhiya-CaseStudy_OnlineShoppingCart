package com.shoppingcart.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.entity.Orders;
import com.shoppingcart.order.exception.AddressNotFoundException;
import com.shoppingcart.order.exception.OrdersNotFoundException;
import com.shoppingcart.order.repository.AddressRepository;
import com.shoppingcart.order.service.OrderServiceImpl;


@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	OrderServiceImpl orderserviceimpl;
	
	@Autowired
	AddressRepository addressrepository;

	
	//Logger logger = LoggerFactory.getLogger(LoggerResponse.class);
	
	@GetMapping("/allorders")
	public ResponseEntity<List<Orders>> getAllOrders(){
		logger.info("Sending request to the Order");
		List<Orders> o1 = orderserviceimpl.getAllOrders();
		logger.info("get all orders");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/alladdress/{name}")
	public ResponseEntity<List<Address>> getAllAddressByCustomerName(@PathVariable String name) throws AddressNotFoundException{
		logger.info("Sending request to the Order");
		List<Address> o1 = orderserviceimpl.getAllAddressByCustomerName(name);
		logger.info("get orders by address");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	@GetMapping("/getorderbyid/{id}")
	public  ResponseEntity<Orders> getOrderByOrderId(@PathVariable int id) throws OrdersNotFoundException{
		logger.info("Sending request to the Order");
		Orders o1 = orderserviceimpl.getOrderByOrderId(id);
		logger.info("get orders by orderid");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
//	@GetMapping("/getorderbyaddress/{id}")
//	public ResponseEntity<List<Address>> getOrderByCustomerId(@PathVariable Integer id) throws AddressNotFoundException {
//		logger.info("Sending request to the Order");
//		List<Address> o1 = orderserviceimpl.getOrderByCustomerId(id);
//		logger.info("get order by customerid");
//		return new ResponseEntity<>(o1,HttpStatus.CREATED);
//	}
	
	@GetMapping("/getOrderByCustomerName/{name}")
	public ResponseEntity<List<Orders>> getOrdersByAddress(@PathVariable String name) throws OrdersNotFoundException{
		logger.info("Sending request to the Order");
		List<Orders> o1 = orderserviceimpl.getAllOrdersByCustomerName(name);
		logger.info("get order by address");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	@PostMapping("/placeorder/{id}")
	public ResponseEntity<Orders> placeorders(@Valid @RequestBody Orders order,@PathVariable int id) throws OrdersNotFoundException {
		logger.info("Sending request to the Order");
		Orders o1 = orderserviceimpl.placeorders(order,id);
		logger.info("order placed");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	@PostMapping("/storeaddress")
	public ResponseEntity<Address> storeAddress(@RequestBody Address address) {
		logger.info("Sending request to the Order");
		Address o1 = orderserviceimpl.storeAddress(address);
		logger.info("address stored");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	@PutMapping("/changeorder/{status}/{id}")
	public ResponseEntity<Orders> changeOrderStatus(@PathVariable String status,@PathVariable int id) {
		logger.info("Sending request to the Order");
		Orders o1 = orderserviceimpl.changeOrderStatus(status, id);
		logger.info("order status changed");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrderById(@PathVariable int id)throws OrdersNotFoundException {
		logger.info("Sending request to the Order");
		String o1 = orderserviceimpl.deleteOrder(id);
		logger.info("deleted");
		return new ResponseEntity<>(o1,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllAddress")
	public ResponseEntity<List<Address>> getAll(){
		List<Address> l1 = orderserviceimpl.getAllAddress();
		return new ResponseEntity<>(l1,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAddress/{id}")
	public ResponseEntity<String> deletedAddress(@PathVariable int id){
		String s1 = orderserviceimpl.deleteAddress(id);
		return new ResponseEntity<String>(s1,HttpStatus.OK);
	}
}
