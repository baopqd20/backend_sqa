package com.example.demo.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Customer.Customer;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findAllByCustomer(Customer user);

    @Query(value = "SELECT * FROM Loans l WHERE l.status = 2 AND l.customer_id = :id", nativeQuery = true)
    List<Loan> findAllActiveLoan(@Param("id") Long id);
}
