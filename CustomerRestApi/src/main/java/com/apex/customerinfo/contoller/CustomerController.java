package com.apex.customerinfo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apex.customerinfo.dto.CustomerDTO;
import com.apex.customerinfo.entity.Customer;
import com.apex.customerinfo.service.CustomerService;

@RestController
@RequestMapping("/customerapi")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping("/customers")
	public List<CustomerDTO> getAllCustomers(){
		List<CustomerDTO> cusDto = service.getAllCustomersFromDb();
		return cusDto;	
	}
	
	@PostMapping("/addCustomer")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO cusDto) {
		return service.saveCustomerToDb(cusDto);
	}
	
	@GetMapping("customers/{id}")
	public CustomerDTO getCustomerById(@PathVariable int id) {
		
		return service.getCustomerByIdFromDb(id);
	}
	
	@PutMapping("edit/{id}")
	public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO cusDto) {
		return service.updateCustomerToDb(id,cusDto);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable int id) {
		service.deleteCustomerFromDb(id);
		return "Deleted Customer Successfully";
	}
	
}
