package com.inventorymanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inventorymanagement.dto.InventoryResponseDTO;
import com.inventorymanagement.dto.OrderDTO;
import com.inventorymanagement.dto.ProductResponseDTO;
import com.inventorymanagement.entity.Order;
import com.inventorymanagement.mapper.OrderMapper;
import com.inventorymanagement.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public OrderDTO placeOrder(OrderDTO odto) {
		//Getting product info by product data
		ProductResponseDTO product = restTemplate.getForObject("http://INVENTORYMANAGEMENTSYSTEM-PRODUCT/productapi/products/"
				+ odto.getProductId(),ProductResponseDTO.class);
		if(product == null) {
			throw new RuntimeException("Product Not Found By Id in Order!");
		}
		
		// ** Getting inventory info by product id in inventory class
		InventoryResponseDTO invent = restTemplate.getForObject("http://INVENTORYMANAGEMENTSYSTEM-INVENTORY/inventoryapi/inventories/" 
		+odto.getProductId() , InventoryResponseDTO.class);
		
		if(invent == null) {
			throw new RuntimeException("Inventory Not Found By Id in Order!");
		}
		
		if(invent.getQuantity() < odto.getQuantity()) {
			throw new RuntimeException("Insufficient Stock!");
		}
		
		restTemplate.put("hhtp://INVENTORYMANAGEMENTSYSTEM-INVENTORY/inventoryapi/reduce/" 
		+odto.getProductId() +"/"+ odto.getQuantity(), null); // since it is not in any particular class it just a api call
		
		double totalPrice = product.getPrice() * odto.getQuantity();
		
		Order order = OrderMapper.mapToEntity(odto);
		order.setProductName(product.getName());
		order.setProductPrice(product.getPrice());

		order.setTotalPrice(totalPrice);

		order.setOrderStatus("Placed");

		order.setOrderDate(LocalDate.now());

		return OrderMapper.mapToDTO(orderRepo.save(order));
	}

	@Override
	public List<OrderDTO> getAllOrders(){
	return orderRepo.findAll().stream()
							  .map(OrderMapper::mapToDTO)
							  .toList();
	 }

	@Override
	public OrderDTO getOrderById(Long id) {
		Order order = orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order Not foung By id"));
		return OrderMapper.mapToDTO(order);
	}

	@Override
	public void cancelOrderById(Long id) {
		Order order = orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order Not foung By id"));
		order.setOrderStatus("CANCELLED");
		orderRepo.save(order);	
	}

}
