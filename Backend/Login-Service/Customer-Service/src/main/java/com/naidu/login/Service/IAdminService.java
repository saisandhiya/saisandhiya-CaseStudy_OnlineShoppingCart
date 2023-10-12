package com.naidu.login.Service;

import java.util.List;

import com.naidu.login.Dto.CartDto;
import com.naidu.login.Dto.OrderDto;
import com.naidu.login.Dto.PaymentDto;
import com.naidu.login.Entity.Login;
import com.naidu.login.Exception.AdminNotFoundException;

public interface IAdminService {

	public CartDto getCartById(int cartId);

	public PaymentDto getPaymentDetails(int transactionId);

	public List<OrderDto> getAllOrders();

	public List<CartDto> getAllCarts();

	public Login addAdmin(Login admin) throws AdminNotFoundException;

	public OrderDto getOrderById(int orderId);

	Login deleteAdmin(String userName) throws AdminNotFoundException;

	Login updateAdmin(String userName, Login admin) throws AdminNotFoundException;

}
