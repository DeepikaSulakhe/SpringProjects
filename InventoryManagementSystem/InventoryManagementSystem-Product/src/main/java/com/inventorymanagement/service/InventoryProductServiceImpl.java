package com.inventorymanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.exception.ProductNotFoundException;
import com.inventorymanagement.mapper.InventoryProductMapper;
import com.inventorymanagement.repository.InventoryProductRepository;

@Service
public class InventoryProductServiceImpl implements InventoryProductService{

	@Autowired
	private InventoryProductRepository productRepo;

	@Override
	public ProductDTO addProduct(ProductDTO pdto) {
		Product product = InventoryProductMapper.mapToEntity(pdto);
		return InventoryProductMapper.mapToDto(productRepo.save(product));
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepo.findAll().stream().map(InventoryProductMapper::mapToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long id) {
		Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found By Id"));
		ProductDTO prodDto = InventoryProductMapper.mapToDto(product);
		return prodDto;
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO pdto) {
		Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found By Id"));
		product.setName(pdto.getName());
		product.setCategory(pdto.getCategory());
		product.setPrice(pdto.getPrice());
		return InventoryProductMapper.mapToDto(productRepo.save(product));
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}	
}
