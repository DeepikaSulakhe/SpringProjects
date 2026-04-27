package com.customerinfo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerinfo.dto.CustomerDTO;
import com.customerinfo.entity.Customer;
import com.customerinfo.exception.CustomerNotFoundException;
import com.customerinfo.mapper.CustomerMapper;
import com.customerinfo.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<CustomerDTO> getAllCustomersFromDb() {
		
		return customerRepo.findAll().stream().map(CustomerMapper::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO saveCustomerToDb(CustomerDTO cusDto) {
		Customer customer = CustomerMapper.mapToEntity(cusDto);
		
		return CustomerMapper.mapToDTO(customerRepo.save(customer));
	}

	@Override
	public CustomerDTO getCustomerByIdFromDb(int id) {
		Customer customer = customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
		return CustomerMapper.mapToDTO(customer);
	}

	@Override
	public CustomerDTO updateCustomerToDb(int id, CustomerDTO cusDto) {
		Customer customer = customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
		customer.setName(cusDto.getName());
		customer.setAge(cusDto.getAge());
		customer.setAddress(cusDto.getAddress());
		customer.setEmail(cusDto.getEmail());
		
		return CustomerMapper.mapToDTO(customerRepo.save(customer));
	}

	@Override
	public void deleteCustomerFromDb(int id) {
		customerRepo.deleteById(id);
	}

}

