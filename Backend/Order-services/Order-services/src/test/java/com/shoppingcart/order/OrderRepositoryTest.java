package com.shoppingcart.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shoppingcart.order.entity.Address;
import com.shoppingcart.order.entity.Cart;
import com.shoppingcart.order.entity.Orders;
import com.shoppingcart.order.entity.Product;
import com.shoppingcart.order.repository.AddressRepository;
import com.shoppingcart.order.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {

	@Mock
	private OrderRepository orderrepository;

	@Mock
	private AddressRepository addressrepository;

	static List<Orders> orders = new ArrayList<>();

	static Orders o = new Orders();

	@BeforeAll
	public static void setUp() {		

		o.setOrderId(99);
		o.setOrderDate(LocalDate.now());
		o.setAmountPaid(1000.00);
		o.setModeOfPayment("Paytm");
		o.setOrderStatus("delivered");
		o.setQuantity(2);
		o.setProductId(44);
		o.setCustomerName("Amy");
		o.setAddress(new Address(3, "sam","sami", "643734983", 44, "Bang", 34782, "KA"));
		o.setCart(new Cart(11, 100.0, new Product(111, "Soap")));
		
		orders.add(o);
		
		Orders o1 = new Orders();
		o1.setOrderId(100);
		o1.setOrderDate(LocalDate.now());
		o1.setAmountPaid(1000.00);
		o1.setModeOfPayment("Paytm");
		o1.setOrderStatus("delivered");
		o1.setQuantity(2);
		o1.setProductId(45);
		o1.setCustomerName("Ram");
		o1.setAddress(new Address(2, "ram","ramesh", "643734984", 23, "Chen", 34783, "TN"));
		o1.setCart(new Cart(12, 120.0, new Product(112, "Shampoo")));
		orders.add(o1);
	}

	@Test
	public void getall() {	

		when(orderrepository.findAll()).thenReturn(orders);
		assertEquals(orders, orderrepository.findAll());
	}
	
	@Test
    public void findByOrderId() {
		Optional<Orders> byorderid = Optional.of(o);
		when(orderrepository.findByOrderId(99)).thenReturn(byorderid);
		assertEquals(byorderid, orderrepository.findByOrderId(99));
    }
	
	@Test
	public void findByCustomerName() {
		List<Orders> bycusName = List.of();
		when(orderrepository.findAllByCustomerName("Ram")).thenReturn(bycusName);
		assertEquals(bycusName, orderrepository.findAllByCustomerName("Ram"));
	}

  

	@Test
	public void getbyorderid() {

		Optional<Orders> byorderid = Optional.of(o);
		when(orderrepository.findByOrderId(99)).thenReturn(byorderid);
		assertEquals(byorderid, orderrepository.findByOrderId(99));
	}

	
	@Test
	public void updatebyid() {


		when(orderrepository.save(o)).thenReturn(o);
		assertEquals(o, orderrepository.save(o));
	}

	@Test
	public void deletebyid() {

		orderrepository.deleteById(99);
		verify(orderrepository).deleteById(99);
	}

	@Test
	public void deleteall() {

		orderrepository.deleteAll();
		verify(orderrepository).deleteAll();
	}
}
