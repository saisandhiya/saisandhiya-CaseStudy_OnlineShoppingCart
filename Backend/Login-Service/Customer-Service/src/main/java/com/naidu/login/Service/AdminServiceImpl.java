package com.naidu.login.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.naidu.login.Dto.CartDto;
import com.naidu.login.Dto.OrderDto;
import com.naidu.login.Dto.PaymentDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.AdminNotFoundException;
import com.naidu.login.Repository.ICustomerRepostiory;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	ICustomerRepostiory repo;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public Login addAdmin(Login admin) throws AdminNotFoundException {
		Optional<Login> c = repo.findByUserName(admin.getUserName());

		if (c.isPresent()) {

			throw new AdminNotFoundException("Admin already exists  with username");
		} else {
			return repo.save(admin);

		}
	}

//	@Override
//	public Login deleteAdmin(int userid) throws AdminNotFoundException {
//		Optional<Login> custopt = repo.findById(userid);
//		if (custopt.isPresent()) {
//			Login c = custopt.get();
//			repo.deleteById(userid);
//			return c;
//		} else {
//			throw new AdminNotFoundException("Admin not found");
//		}
//	}
	@Override
	public Login deleteAdmin(String userName) throws AdminNotFoundException {
		Optional<Login> custopt = repo.findByUserName(userName);
		if (custopt.isPresent()) {
			Login c = custopt.get();
			//repo.de
			repo.delete(c);
			return c;
		} else {
			throw new AdminNotFoundException("Admin not found with username");
		}
	}

//	@Override
//	public Login updateAdmin(int userid, Login admin) throws AdminNotFoundException {
//		Optional<Login> custopt = repo.findById(userid);
//		if (custopt.isPresent()) {
//			Login c1 = custopt.get();
//
//			c1.setEmail(admin.getEmail());
//			c1.setFirstName(admin.getFirstName());
//			c1.setLastName(admin.getLastName());
//			c1.setMobileNumber(admin.getMobileNumber());
//			c1.setPassword(admin.getPassword());
//			c1.setUserName(admin.getUserName());
//			repo.save(c1);
//			return c1;
//		} else {
//			throw new AdminNotFoundException("Admin not found");
//		}
//	}
	
	@Override
	public Login updateAdmin(String userName, Login admin) throws AdminNotFoundException {
		Optional<Login> custopt = repo.findByUserName(userName);
		if (custopt.isPresent()) {
			Login c1 = custopt.get();

			c1.setEmail(admin.getEmail());
			c1.setFirstName(admin.getFirstName());
			c1.setLastName(admin.getLastName());
			c1.setMobileNumber(admin.getMobileNumber());
			c1.setPassword(admin.getPassword());
			c1.setUserName(admin.getUserName());
			repo.save(c1);
			return c1;
		} else {
			throw new AdminNotFoundException("Admin not found");
		}
	}

	public CartDto getCartById(int cartId) {

		ResponseEntity<CartDto> responseEntity = restTemplate.getForEntity("http://localhost:3333/cart/" + cartId,
				CartDto.class);
		return responseEntity.getBody();

	}

	public List<CartDto> getAllCarts() {

		ResponseEntity<CartDto[]> responseEntity = restTemplate.getForEntity("http://localhost:3333/cart/getallcarts",
				CartDto[].class);
		CartDto[] body = responseEntity.getBody();

		return Arrays.asList(body);

	}

	public OrderDto getOrderById(int orderId) {
		ResponseEntity<OrderDto> responseEntity = restTemplate
				.getForEntity("http://localhost:8090/order/getorderbyid/" + orderId, OrderDto.class);
		OrderDto orderDto = responseEntity.getBody();
		return orderDto;
	}

	public List<OrderDto> getAllOrders() {

		ResponseEntity<OrderDto[]> responseEntity = restTemplate.getForEntity("http://localhost:8090/order/allorders",
				OrderDto[].class);
		OrderDto[] orderDto = responseEntity.getBody();
		return Arrays.asList(orderDto);

	}

	public PaymentDto getPaymentDetails(int transactionId) {
		ResponseEntity<PaymentDto> responseEntity = restTemplate
				.getForEntity("http://localhost:8085/payment/getBytId/" + transactionId, PaymentDto.class);

		PaymentDto paymentDto = responseEntity.getBody();
		return paymentDto;
	}

}