package com.inventorymanagement.mapper;



import com.inventorymanagement.dto.OrderDTO;
import com.inventorymanagement.entity.Order;

public class OrderMapper {
	
	public static OrderDTO mapToDTO(Order order) {
		
		OrderDTO odto = new OrderDTO();
		odto.setId(order.getId());
		odto.setProductId(order.getProductId());
		odto.setQuantity(order.getQuantity());
		odto.setProductPrice(order.getProductPrice());
		odto.setTotalPrice(order.getTotalPrice());
		odto.setOrderStatus(order.getOrderStatus());
		odto.setOrderDate(order.getOrderDate());
		odto.setCustomerName(order.getCustomerName());
		odto.setCustomerEmail(order.getCustomerEmail());
		odto.setCustomerAddress(order.getCustomerAddress());
		
		return odto;
	}
	
	public static Order mapToEntity(OrderDTO odto) {
		
		Order order = new Order();
		
		order.setId(odto.getId());
		order.setProductId(odto.getProductId());
		order.setQuantity(odto.getQuantity());
		order.setProductPrice(odto.getProductPrice());
		order.setTotalPrice(odto.getTotalPrice());
		order.setOrderStatus(odto.getOrderStatus());
		order.setOrderDate(odto.getOrderDate());
		order.setCustomerName(odto.getCustomerName());
		order.setCustomerEmail(odto.getCustomerEmail());
		order.setCustomerAddress(odto.getCustomerAddress());
		
		return order;
	}

}
