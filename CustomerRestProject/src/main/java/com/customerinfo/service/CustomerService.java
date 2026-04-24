package com.customerinfo.service;

import java.util.List;

import com.customerinfo.dto.CustomerDTO;


public interface CustomerService {

	List<CustomerDTO> getAllCustomersFromDb();

	CustomerDTO saveCustomerToDb(CustomerDTO cusDto);

	CustomerDTO getCustomerByIdFromDb(int id);

	CustomerDTO updateCustomerToDb(int id, CustomerDTO cusDto);

	void deleteCustomerFromDb(int id);

}
