package com.shoppingcart.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.repository.AddressRepository;

@ExtendWith(MockitoExtension.class)
public class AddressRepositoryTest {

	
	@Mock
	private AddressRepository addressrepository;

	static List<Address> address = new ArrayList<>();

	static Address a = new Address();
	
	@BeforeAll
	public static void setUp() {		 

		a.setCustomerId(101);
		a.setFullName("Ram");
		a.setMobileNumber("9876256789");
		a.setFlatNumber(32);
		a.setCity("Kora");
		a.setPincode(6543234);
		a.setState("Karnataka");

		address.add(a);
	}

	@Test
	public void getAll() {
			
		
		when(addressrepository.findAll()).thenReturn(address);
		assertEquals(address,addressrepository.findAll());
	}

	@Test
	public void findByCustomerId() {
		
		
		when(addressrepository.findByCustomerId(101)).thenReturn(a);
		assertEquals(a,addressrepository.findByCustomerId(101));
		
	}
	
	@Test
	public void findByCustomerName() {
		
		
		when(addressrepository.findByCustomerName("john")).thenReturn(a);
		assertEquals(a,addressrepository.findByCustomerName("john"));
		
	}
	
	@Test
	public void findAllByCustomerName() {
		
		
		when(addressrepository.findAllByCustomerName("john")).thenReturn(address);
		assertEquals(address,addressrepository.findAllByCustomerName("john"));
		
	}

	@Test
	public void findByFullName() {	
		
		
		when(addressrepository.findByFullName("Ram")).thenReturn(address);
		assertEquals(address,addressrepository.findByFullName("Ram"));		
		
	}
	
	@Test
	public void updateById() {
		when(addressrepository.save(a)).thenReturn(a);
		assertEquals(a,addressrepository.save(a));
	}
	
	@Test
	public void deleteById() {
		
		addressrepository.deleteById(101);
		verify(addressrepository).deleteById(101);
	}
	
	@Test
	public void deleteAll() {
		
		addressrepository.deleteAll();
		verify(addressrepository).deleteAll();
	}

}
