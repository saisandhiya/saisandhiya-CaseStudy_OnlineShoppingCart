package com.naidu.CartService;

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

import com.naidu.CartService.entity.Cart;
import com.naidu.CartService.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
public class CartRepositoryTest {
	
	@Mock
	private CartRepository cartrepository;
	
	static Cart c = new Cart();
	
	static List<Cart> carts = new ArrayList<>();
	
	@BeforeAll
	public static void setUp() {
		
		c.setCartId(1);
		c.setTotalPrice(20000.0);
		
		carts.add(c);		
	}
	
	@Test
	public void getall() {	

		when(cartrepository.findAll()).thenReturn(carts);
		assertEquals(carts, cartrepository.findAll());
	}
	
	@Test
    public void findById() {
		
		when(cartrepository.findById(1)).thenReturn(c);
		assertEquals(c, cartrepository.findById(1));
    }
	
	@Test
	public void updatebyid() {


		when(cartrepository.save(c)).thenReturn(c);
		assertEquals(c, cartrepository.save(c));
	}

	@Test
	public void deletebyid() {

		cartrepository.deleteById(1);
		verify(cartrepository).deleteById(1);
	}

	@Test
	public void deleteall() {

		cartrepository.deleteAll();
		verify(cartrepository).deleteAll();
	}
	

}
