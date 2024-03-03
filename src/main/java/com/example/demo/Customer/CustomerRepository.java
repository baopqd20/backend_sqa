package com.example.demo.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Customer.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer save(Customer customer);
	Customer saveAndFlush(Customer customer);
	void deleteById(Long id);
	Optional<Customer> findById(Long id);
	List<Customer> findAll();
	
  
}
