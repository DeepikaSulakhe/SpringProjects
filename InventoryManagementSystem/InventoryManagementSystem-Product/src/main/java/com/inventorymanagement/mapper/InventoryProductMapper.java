package com.inventorymanagement.mapper;

import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.entity.Product;

public class InventoryProductMapper {
	
	public static ProductDTO mapToDto(Product product) {
		ProductDTO prodDto = new ProductDTO();
		prodDto.setId(product.getId());
		prodDto.setName(product.getName());
		prodDto.setCategory(product.getCategory());
		prodDto.setPrice(product.getPrice());
		
		return prodDto;
	}
	
	public static Product mapToEntity(ProductDTO prodDto) {
	     Product product = new Product();
	     product.setId(prodDto.getId());
	     product.setName(prodDto.getName());
	     product.setCategory(prodDto.getCategory());
	     product.setPrice(prodDto.getPrice());
	     
	     return product;
	}

}
