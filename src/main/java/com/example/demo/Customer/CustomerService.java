package com.example.demo.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer saveandflush(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}
	
	public void deleteById (Long id) {
		customerRepository.deleteById(id);
	}
	
	public Optional<Customer> findById(Long id){
		return customerRepository.findById(id);
		
	}
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	
}
