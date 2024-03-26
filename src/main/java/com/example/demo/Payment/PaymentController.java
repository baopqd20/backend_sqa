package com.example.demo.Payment;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Loan.Loan;
import com.example.demo.Loan.LoanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final LoanService loanService;

    @PostMapping("/pay-loan")
    @Transactional(rollbackFor = { Exception.class })
    public ResponseEntity<Object> payLoan(@RequestBody @Valid PaymentRequest request) {
        Loan loan = loanService.getDetailLoan(request.getLoan_id());

        if (loan.getLoanAmount() > request.getAmount()) {
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
        Double remaining = loan.getLoanAmount();
        loan.setLoanAmount(0.0);
        loan.setStatus(3);
        loanService.createUserLoan(loan);
        Payment payment = Payment.builder()
                .amount(remaining)
                .loan(loan)
                .payDate(new Date())
                .build();
        Boolean paymentRes = paymentService.payLoan(payment);
        if (paymentRes) {
            PaymentResponse response = PaymentResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Pay loan successfully")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            PaymentResponse response = PaymentResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Please try again later")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> paymentHistory(@PathVariable String id) {
        Loan loan = loanService.getDetailLoan(id);
        PaymentResponse response = PaymentResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get history successfully")
                .data(paymentService.getPaymentHistory(loan))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
