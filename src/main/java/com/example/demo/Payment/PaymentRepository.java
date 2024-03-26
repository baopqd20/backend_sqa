package com.example.demo.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.demo.Loan.Loan;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByLoan(Loan loan);
}
