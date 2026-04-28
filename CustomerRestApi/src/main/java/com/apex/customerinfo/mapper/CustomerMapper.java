package com.apex.customerinfo.mapper;

import com.apex.customerinfo.dto.CustomerDTO;
import com.apex.customerinfo.entity.Customer;

public class CustomerMapper {
	
	public static CustomerDTO mapToDTO(Customer customer) {
		CustomerDTO cdto = new CustomerDTO();
		cdto.setId(customer.getId());
		cdto.setName(customer.getName());
		cdto.setAge(customer.getAge());
		cdto.setAddress(customer.getAddress());
		cdto.setEmail(customer.getEmail());
		return cdto;
		
	}
	
	public static Customer mapToEntity(CustomerDTO cusDto) {
		Customer customer = new Customer();
		customer.setId(cusDto.getId());
		customer.setName(cusDto.getName());
		customer.setAge(cusDto.getAge());
		customer.setAddress(cusDto.getAddress());
		customer.setEmail(cusDto.getEmail());
		return customer;
		
	}

}
