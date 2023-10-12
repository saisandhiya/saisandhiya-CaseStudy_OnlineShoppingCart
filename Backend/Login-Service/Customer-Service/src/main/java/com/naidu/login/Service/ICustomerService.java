package com.naidu.login.Service;

import java.util.List;

import com.naidu.login.Dto.ProductDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.CustomerNotFoundException;
import com.naidu.login.Exception.ProductNotFoundException;

public interface ICustomerService {

	public Login addCustomer(Login customer);

	// public Login deleteCustomer(int userid);

//	Login updateCustomer(int userid, Login customer);
	Login updateCustomer(Login customer);

	public List<Login> getCustomers();

	// public Login getCustomerByid(String customerId);

	// public String getCustomerEmail(int customerId);

	// public String getCustomerPassword(int customerId);

	// public Login getCustomerByEmail(String email);

	public ProductDto viewProductById(int id) throws ProductNotFoundException;

	public List<ProductDto> viewAllProducts();

	Login updateCustomerByEmail(String email, Login customer);

	Login register(Login user);

	Login deleteCustomer(String username) throws CustomerNotFoundException;

	Login getCustomerByUserName(String username);

	Login getCustomerByEmail(String email);

	String getCustomerEmail(String userName);

	String getCustomerPassword(String userName);

	// String getCustomerPassword(String userName);
}
