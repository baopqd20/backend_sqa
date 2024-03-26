package com.example.demo.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Customer.Customer;

public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findAllByCustomer(Customer user);

    @Query(value = "SELECT * from Loans l WHERE l.status = 2", nativeQuery = true)
    List<Loan> findAllActiveLoan();
}
