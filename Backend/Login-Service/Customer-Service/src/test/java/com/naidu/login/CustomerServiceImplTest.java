package com.naidu.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.naidu.login.Dto.ProductDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.CustomerAlreadyExistException;
import com.naidu.login.Exception.CustomerIdNotFoundException;
import com.naidu.login.Exception.CustomerNotFoundException;
import com.naidu.login.Exception.ProductNotFoundException;
import com.naidu.login.Repository.ICustomerRepostiory;
import com.naidu.login.Service.CustomerServiceImpl;

public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private ICustomerRepostiory customerRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCustomer() {
		Login customer = new Login();
		customer.setUserName("customer");
		when(customerRepository.findByUserName(customer.getUserName())).thenReturn(Optional.empty());
		when(customerRepository.save(customer)).thenReturn(customer);

		Login result = customerService.addCustomer(customer);

		assertEquals(customer, result);
	}

	@Test
	public void testAddCustomerCustomerAlreadyExists() {
		Login customer = new Login();
		customer.setUserName("customer");
		when(customerRepository.findByUserName(customer.getUserName())).thenReturn(Optional.of(customer));

		assertThrows(CustomerNotFoundException.class, () -> customerService.addCustomer(customer));
	}

	@Test
	public void testDeleteCustomer() throws CustomerNotFoundException {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);
		when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));

		Login result = customerService.deleteCustomer(username);

		assertEquals(customer, result);
	}

	@Test
	public void testDeleteCustomerCustomerNotFound() {
		String username = "customer";
		when(customerRepository.findByUserName(username)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(username));
	}

	@Test
	public void testUpdateCustomer() throws CustomerNotFoundException {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);

		Login updatedCustomer = new Login();
		updatedCustomer.setUserName(username);
		updatedCustomer.setFirstName("UpdatedFirstName");
		updatedCustomer.setLastName("UpdatedLastName");
		updatedCustomer.setEmail("updated@example.com");
		updatedCustomer.setMobileNumber("1234567890");

		when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));
		when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

		Login result = customerService.updateCustomer(updatedCustomer);

		assertEquals(updatedCustomer, result);
	}

	@Test
	public void testUpdateCustomerCustomerNotFound() {
		String username = "customer";
		Login updatedCustomer = new Login();
		updatedCustomer.setUserName(username);

		when(customerRepository.findByUserName(username)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(updatedCustomer));
	}

	@Test
	public void testGetCustomers() {
		List<Login> customers = Arrays.asList(
				new Login("customer1", "FirstName1", "LastName1", "email1@example.com", "1234567890", "password1",
						"ROLE_CUSTOMER"),
				new Login("customer2", "FirstName2", "LastName2", "email2@example.com", "1234567891", "password2",
						"ROLE_CUSTOMER"));
		when(customerRepository.findAll()).thenReturn(customers);

		List<Login> result = customerService.getCustomers();

		assertEquals(2, result.size());
	}

	@Test
	public void testGetCustomerByUserName() {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);
		when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));

		Login result = customerService.getCustomerByUserName(username);

		assertEquals(customer, result);
	}

	/*
	 * @Test public void testGetCustomerByUserNameCustomerNotFound() { // Arrange
	 * String username = "nonexistentuser";
	 * when(customerRepository.findByUserName(username)).thenReturn(Optional.empty()
	 * );
	 * 
	 * // Act and Assert assertThrows(CustomerNotFoundException.class, () ->
	 * customerService.getCustomerByUserName(username)); }
	 */

	@Test
	public void testGetCustomerEmail() {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);
		customer.setEmail("customer@example.com");
		when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));

		String result = customerService.getCustomerEmail(username);

		assertEquals(customer.getEmail(), result);
	}

	@Test
	public void testGetCustomerEmailCustomerNotFound() {
		String username = "customer@gmail.com";
		when(customerRepository.findByUserName(username)).thenReturn(Optional.empty());

		assertThrows(CustomerIdNotFoundException.class, () -> customerService.getCustomerEmail(username));
	}

	@Test
	public void testGetCustomerPassword() {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);
		customer.setPassword("password");
		when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));

		String result = customerService.getCustomerPassword(username);

		assertEquals(customer.getPassword(), result);
	}

	@Test
	public void testGetCustomerPasswordCustomerNotFound() {
		String username = "customer";
		when(customerRepository.findByUserName(username)).thenReturn(Optional.empty());

		assertThrows(CustomerIdNotFoundException.class, () -> customerService.getCustomerPassword(username));
	}

	@Test
	public void testGetCustomerByEmail() {
		String email = "customer@example.com";
		Login customer = new Login();
		customer.setEmail(email);
		when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

		Login result = customerService.getCustomerByEmail(email);

		assertEquals(customer, result);
	}

	@Test
	public void testGetCustomerByEmailCustomerNotFound() {
		String email = "customer@example.com";
		when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerByEmail(email));
	}

	@Test
	public void testViewProductById() {
		int productId = 1;
		ProductDto productDto = new ProductDto();
		ResponseEntity<ProductDto> responseEntity = ResponseEntity.ok(productDto);

		when(restTemplate.getForEntity("http://localhost:3334/product/getproduct/" + productId, ProductDto.class))
				.thenReturn(responseEntity);

		ProductDto result = customerService.viewProductById(productId);

		assertEquals(productDto, result);
	}

	@Test
	public void testViewProductByIdProductNotFound() {
		int productId = 1;
		when(restTemplate.getForEntity("http://localhost:3334/product/getproduct/" + productId, ProductDto.class))
				.thenThrow(HttpClientErrorException.class);

		assertThrows(ProductNotFoundException.class, () -> customerService.viewProductById(productId));
	}

	@Test
	public void testViewAllProducts() {
		ProductDto[] productDtos = { new ProductDto(), new ProductDto() };
		ResponseEntity<ProductDto[]> responseEntity = ResponseEntity.ok(productDtos);

		when(restTemplate.getForEntity("http://localhost:3334/product/products", ProductDto[].class))
				.thenReturn(responseEntity);

		List<ProductDto> result = customerService.viewAllProducts();

		assertEquals(2, result.size());
	}

	@Test
	public void testViewAllProductsProductNotFound() {
		when(restTemplate.getForEntity("http://localhost:3334/product/products", ProductDto[].class))
				.thenThrow(HttpClientErrorException.class);

		assertThrows(ProductNotFoundException.class, () -> customerService.viewAllProducts());
	}

	@Test
	public void testUpdateCustomerByEmail() throws CustomerNotFoundException {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);

		when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));
		when(customerRepository.save(customer)).thenReturn(customer);

		Login result = customerService.updateCustomerByEmail(username, customer);

		assertEquals(customer, result);
	}

	@Test
	public void testUpdateCustomerByEmailCustomerNotFound() {
		String username = "customer";
		Login customer = new Login();
		customer.setUserName(username);

		when(customerRepository.findByUserName(username)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomerByEmail(username, customer));
	}

	@Test
	public void testRegister() {
		Login user = new Login();
		user.setUserName("user");
		user.setEmail("user@example.com");

		when(customerRepository.findByUserName(user.getUserName())).thenReturn(Optional.empty());
		when(customerRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
		when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
		when(customerRepository.save(user)).thenReturn(user);

		Login result = customerService.register(user);

		assertEquals(user, result);
	}

	@Test
	public void testRegisterUsernameAlreadyExists() {
		Login user = new Login();
		user.setUserName("user");

		when(customerRepository.findByUserName(user.getUserName())).thenReturn(Optional.of(user));

		assertThrows(CustomerAlreadyExistException.class, () -> customerService.register(user));
	}

	@Test
	public void testRegisterEmailAlreadyExists() {
		Login user = new Login();
		user.setEmail("user@example.com");

		when(customerRepository.findByUserName(user.getUserName())).thenReturn(Optional.empty());
		when(customerRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

		assertThrows(CustomerAlreadyExistException.class, () -> customerService.register(user));
	}
}
