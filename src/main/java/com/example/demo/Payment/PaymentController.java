package com.example.demo.Payment;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Loan.Loan;
import com.example.demo.Loan.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final LoanService loanService;

    @PostMapping("/{id}")
    @Transactional(rollbackFor = { Exception.class })
    public ResponseEntity<Object> payLoan(@PathVariable String id, @RequestParam PaymentRequest request) {
        Loan loan = loanService.getDetailLoan(id);

        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .loan(loan)
                .payDate(new Date())
                .build();
        Boolean paymentRes = paymentService.payLoan(payment);
        if (paymentRes) {
            Boolean loanRes = loanService.payLoan(loan, request.getAmount());
            if (loanRes) {
                PaymentResponse response = PaymentResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Pay loan successfully")
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        PaymentResponse response = PaymentResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Please try again later")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
