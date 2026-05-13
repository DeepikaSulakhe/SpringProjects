package com.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventorymanagement.dto.InventoryDTO;
import com.inventorymanagement.service.InventoryService;

@RestController
@RequestMapping("/inventoryapi")
public class InventoryController {
	
	@Autowired
	private InventoryService service;
	
	@PostMapping("/addInventory")
	public ResponseEntity<InventoryDTO> saveInventoryToDb(@RequestBody InventoryDTO inventDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addInventory(inventDto));
	}
	
	@GetMapping("/inventories")
	public ResponseEntity<List<InventoryDTO>> getAllInventoryFromDb(){
		return ResponseEntity.ok(service.getAllInventory());
	} 
	
	@GetMapping("/inventories/{productId}")
	public ResponseEntity<InventoryDTO> getInventoryByProductFromDb(@PathVariable Long productId){
		return ResponseEntity.ok(service.getInventoryByProductId(productId));
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<InventoryDTO> updateInventoryByIdInDb(@PathVariable Long id, @RequestBody InventoryDTO idto){
		return ResponseEntity.ok(service.updateInventoryById(id, idto));
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteInventoryByIdInDb(@PathVariable Long id){
		service.deleteInventory(id);
		return ResponseEntity.ok("Deleted the Inventory Successfully");
	}
	@PutMapping("/reduce/{productId}/{quantity}")
	public ResponseEntity<String> reducingStockByProductId(@PathVariable Long productId, @PathVariable Integer quantity){
		
		return ResponseEntity.ok(service.reduceStock(productId, quantity));	
	}
	
	}
  
