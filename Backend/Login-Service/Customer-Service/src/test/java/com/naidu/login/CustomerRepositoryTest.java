package com.naidu.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.naidu.login.Entity.Login;
import com.naidu.login.Repository.ICustomerRepostiory;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {
	
	@Mock
	private ICustomerRepostiory icustomerrepo;
	
	
	static List<Login> login = new ArrayList<>();
	
	static Login l = new Login();
	
	@BeforeAll
	public static void setUp() {
		l.setUserName("sai");
		l.setFirstName("sais");
		l.setLastName("R");
		l.setMobileNumber("9876567897");
		l.setEmail("sai@gmail.com");
		l.setPassword("sai1234");
		l.setRole("admin");
		
		login.add(l);
	}
	
	@Test
	public void getall() {	

		when(icustomerrepo.findAll()).thenReturn(login);
		assertEquals(login, icustomerrepo.findAll());
	}
	
	
	@Test
	public void findByCustomerName() {
		Optional<Login> bycusName = Optional.of(l);
		when(icustomerrepo.findByUserName("Ram")).thenReturn(bycusName);
		assertEquals(bycusName, icustomerrepo.findByUserName("Ram"));
	} 
	
	@Test
	public void findByEmail() {
		Optional<Login> byemail = Optional.of(l);
		when(icustomerrepo.findByEmail("sai@gmail.com")).thenReturn(byemail);
		assertEquals(byemail, icustomerrepo.findByEmail("sai@gmail.com"));
	}



	@Test
	public void deleteall() {

		icustomerrepo.deleteAll();
		verify(icustomerrepo).deleteAll();
	}

}
