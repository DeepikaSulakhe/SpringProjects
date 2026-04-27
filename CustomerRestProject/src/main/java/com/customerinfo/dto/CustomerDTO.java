package com.customerinfo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
	
	private int id;
	
	@NotBlank(message = "Customer name is required")
	@Size(max=50,message="Name must below 50 Characters")
	private String name;
	
	@NotNull(message="Age is required")
	@Positive(message = "Age must be greater than 0")
	private int age;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email")
	private String email;
	
	public CustomerDTO() {
		
	}

	public CustomerDTO( String name, int age, String address, String email) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}

