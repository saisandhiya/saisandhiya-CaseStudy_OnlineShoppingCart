package com.naidu.login.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.naidu.login.Dto.ProductDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.CustomerAlreadyExistException;
import com.naidu.login.Exception.CustomerIdNotFoundException;
import com.naidu.login.Exception.CustomerNotFoundException;
import com.naidu.login.Exception.ProductNotFoundException;
import com.naidu.login.Repository.ICustomerRepostiory;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepostiory customerrepo;
	@Autowired
	PasswordEncoder pen;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Login addCustomer(Login customer) {
		Optional<Login> c = customerrepo.findByUserName(customer.getUserName());

		if (c.isPresent()) {

			throw new CustomerNotFoundException("customer already exists  with email");
		} else {
			return customerrepo.save(customer);

		}

	}

	@Override
	public Login deleteCustomer(String username) throws CustomerNotFoundException {
		Optional<Login> custopt = customerrepo.findByUserName(username);
		if (custopt.isPresent()) {
			Login c = custopt.get();
			customerrepo.delete(c);
			return c;
		} else {
			throw new CustomerNotFoundException("customer not found");
		}

	}

	@Override
	public Login updateCustomer(Login customer) throws CustomerNotFoundException {
//		Optional<Login> custopt = customerrepo.findById(userid);
		Optional<Login> custopt = customerrepo.findByUserName(customer.getUserName());
		if (custopt.isPresent()) {
			Login c1 = custopt.get();
			c1.setEmail(customer.getEmail());
			c1.setFirstName(customer.getFirstName());
			c1.setLastName(customer.getLastName());
			c1.setMobileNumber(customer.getMobileNumber());
//			c1.setPassword(pen.encode(customer.getPassword()));
//			c1.setUserName(customer.getUserName());
			customerrepo.save(c1);
			return c1;
		} else {
			throw new CustomerNotFoundException("customer not found");
		}

	}

	@Override
	public List<Login> getCustomers() {
		List<Login> l1 = (List<Login>) customerrepo.findAll();
		return l1.stream().filter(lo -> lo.getRole().equals("ROLE_CUSTOMER")).collect(Collectors.toList());
	}

	@Override
	public Login getCustomerByUserName(String username) {
		Optional<Login> opt = customerrepo.findByUserName(username);
		try {
			if (opt.isPresent()) {
				Login c = opt.get();
				return c;
			} else {
				throw new CustomerNotFoundException("Customer  not found: ");
			}
		} catch (CustomerNotFoundException ex) {
			System.out.println("Customer  not found: " + username);
		}
		return null;
	}

//	@Override
//	public String getCustomerEmail(int customerId) {
//
//		Optional<Login> opt = customerrepo.findById(customerId);
//		if (opt.isPresent()) {
//			Login c = opt.get();
//			return c.getEmail();
//		} else {
//			throw new CustomerIdNotFoundException("Customer not found");
//		}
//
//	}
	@Override
	public String getCustomerEmail(String userName) {

		Optional<Login> opt = customerrepo.findByUserName(userName);
		if (opt.isPresent()) {
			Login c = opt.get();
			return c.getEmail();
		} else {
			throw new CustomerIdNotFoundException("Customer not found");
		}

	}

//	@Override
//	public String getCustomerPassword(int customerId) {
//		Optional<Login> opt = customerrepo.findById(customerId);
//		if (opt.isPresent()) {
//			Login c = opt.get();
//			return c.getPassword();
//		} else {
//			throw new CustomerIdNotFoundException("Customer not found");
//		}
//
//	}
	
	
	@Override
	public String getCustomerPassword(String userName) {
		Optional<Login> opt = customerrepo.findByUserName(userName);
		if (opt.isPresent()) {
			Login c = opt.get();
			return c.getPassword();
		} else {
			throw new CustomerIdNotFoundException("Customer not found");
		}

	}

	@Override
	public Login getCustomerByEmail(String email) {
		Optional<Login> c = customerrepo.findByEmail(email);

		if (c.isPresent()) {
			Login cus = c.get();
			return cus;
		} else {
			throw new CustomerNotFoundException("customer not found with email");

		}

	}

	@Override
	public ProductDto viewProductById(int id) {
		try {

			ResponseEntity<ProductDto> responseEntity = restTemplate
					.getForEntity("http://localhost:3334/product/getproduct/" + id, ProductDto.class);

			ProductDto productDto = responseEntity.getBody();
			return productDto;
		} catch (HttpClientErrorException ex) {
			throw new ProductNotFoundException("product not found");

		}

	}

	@Override
	public List<ProductDto> viewAllProducts() {
		try {

			ResponseEntity<ProductDto[]> responseEntity = restTemplate.getForEntity("http://localhost:3334/product/products",
					ProductDto[].class);
			ProductDto[] productDtos = responseEntity.getBody();
			return Arrays.asList(productDtos);
		} catch (HttpClientErrorException ex) {
			throw new ProductNotFoundException("Products not found");

		}

	}

	@Override
	public Login updateCustomerByEmail(String customerName, Login customer) {
		Optional<Login> custopt = customerrepo.findByUserName(customerName);
		if (custopt.isPresent()) {
			Login c1 = custopt.get();

			c1.setEmail(customer.getEmail());
			c1.setFirstName(customer.getFirstName());
			c1.setLastName(customer.getLastName());
			c1.setMobileNumber(customer.getMobileNumber());
			c1.setPassword(customer.getPassword());
			c1.setUserName(customer.getUserName());
			customerrepo.save(c1);
			return c1;
		} else {
			throw new CustomerNotFoundException("customer not found");
		}
	}

	@Override
	public Login register(Login user) {
		//Optional<Login> op = customerrepo.findById(user.getLoginId());
		Optional<Login> email = customerrepo.findByEmail(user.getEmail());
		Optional<Login> name = customerrepo.findByUserName(user.getUserName());
		if (name.isPresent()) {
			throw new CustomerAlreadyExistException("user Already Exists with the given username " + user.getUserName());
		}
//		if (op.isPresent()) {
//			throw new CustomerIdNotFoundException("user Already Exists with the given id " + user.getLoginId());
//		}
		if(email.isPresent()) {
			throw new CustomerAlreadyExistException("user Already exists with the given email "+user.getEmail());
		}
		user.setPassword(pen.encode(user.getPassword()));
		return customerrepo.save(user);
	}

	

}
