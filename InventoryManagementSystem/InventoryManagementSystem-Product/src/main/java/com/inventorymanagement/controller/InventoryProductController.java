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

import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.service.InventoryProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productapi")
public class InventoryProductController {
	
	@Autowired
	private InventoryProductService service;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductDTO> saveProductToService(@Valid @RequestBody ProductDTO pdto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(pdto));
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProductsFromService(){
		return ResponseEntity.ok(service.getAllProducts());
	} 
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDTO>  getProductFromService(@PathVariable Long id) {
		return ResponseEntity.ok(service.getProductById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id, @Valid @RequestBody ProductDTO pdto) {
		return ResponseEntity.ok(service.updateProduct(id, pdto));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>  deleteProductById(@PathVariable Long id) {
		service.deleteProduct(id);
		return ResponseEntity.ok("Product Deleted Successfully!!");
	}
}
