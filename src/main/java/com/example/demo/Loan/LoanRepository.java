package com.example.demo.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Customer.Customer;

public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findAllByCustomer(Customer user);
}
