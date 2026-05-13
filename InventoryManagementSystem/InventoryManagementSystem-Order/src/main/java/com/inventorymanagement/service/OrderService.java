package com.inventorymanagement.service;

import java.util.List;

import com.inventorymanagement.dto.OrderDTO;

public interface OrderService {
	
	OrderDTO placeOrder(OrderDTO odto);
	List<OrderDTO> getAllOrders();
	OrderDTO getOrderById(Long id);
	void cancelOrderById(Long id);
}
