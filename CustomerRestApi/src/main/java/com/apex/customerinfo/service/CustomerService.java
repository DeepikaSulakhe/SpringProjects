package com.apex.customerinfo.service;

import java.util.List;

import com.apex.customerinfo.dto.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> getAllCustomersFromDb();

	CustomerDTO saveCustomerToDb(CustomerDTO cusDto);

	CustomerDTO getCustomerByIdFromDb(int id);

	CustomerDTO updateCustomerToDb(int id, CustomerDTO cusDto);

	void deleteCustomerFromDb(int id);

}
