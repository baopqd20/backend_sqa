package com.example.demo.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@org.springframework.transaction.annotation.Transactional(rollbackFor = {Exception.class})
	public Customer save(Customer customer) {
		Customer registerCustomer = customerRepository.save(customer);
		return registerCustomer;
	}

	public Customer saveandflush(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);

	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	public List<Customer> searchCustomer(String name) {
		return customerRepository.findByName(name);
	}

	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).get();
	}
}
