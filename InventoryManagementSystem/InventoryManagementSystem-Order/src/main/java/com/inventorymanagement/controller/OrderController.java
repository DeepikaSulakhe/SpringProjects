package com.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventorymanagement.dto.OrderDTO;
import com.inventorymanagement.service.OrderService;

@RestController
@RequestMapping("/orderapi")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping("/addOrder")
	public ResponseEntity<OrderDTO> insrtOrderFromService(@RequestBody OrderDTO odto){

		return ResponseEntity.status(HttpStatus.CREATED).body(service.placeOrder(odto));
	}
	
	@GetMapping("/orders") 
	public ResponseEntity<List<OrderDTO>> getAllOrdersFromService(){
		
		return ResponseEntity.ok(service.getAllOrders());
	}
	
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDTO> getOrderByOrderId(@PathVariable Long id){
		
		return ResponseEntity.ok(service.getOrderById(id));
	}
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<String> cancelTheOrderFromService(@PathVariable Long id){
		
		service.cancelOrderById(id);
		return ResponseEntity.ok("Order Cancelled Successfully!");
		
	}

}
