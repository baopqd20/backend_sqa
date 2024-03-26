package com.example.demo.Payment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Loan.Loan;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional(rollbackFor = {Exception.class})
    public Boolean payLoan(Payment request) {
        Payment payment = paymentRepository.save(request);
        if(payment == null) {
            return false;
        }
        return true;
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).get();
    }

    public List<Payment> getPaymentHistory(Loan loan) {
        return paymentRepository.findByLoan(loan);
    }
}
