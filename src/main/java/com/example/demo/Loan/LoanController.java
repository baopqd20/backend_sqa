package com.example.demo.Loan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;

    private final CustomerService customerService;

    @GetMapping("/all/{id}")
    public ResponseEntity<Object> getAllLoan(@PathVariable Long id) {
        LoanResponse loanResponse = LoanResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get all loan successfully")
                .data(loanService.getAllUserLoan(id))
                .build();
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetailLoan(@PathVariable String id) {
        LoanResponse response = LoanResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get detail loan successfully")
                .data(loanService.getDetailLoan(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createLoan(@RequestBody LoanRequest request) {
        Customer customer = customerService.getCustomer(request.getCustomer_id());
        Loan loan = Loan.builder()
                .customer(customer)
                .interestRate(1.0)
                .startDate(request.getStart_date())
                .loanAmount(request.getLoan_amount())
                .loanTerm(request.getLoan_term())
                .status(0)
                .build();
        LoanResponse response = LoanResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Loan created successfully")
                .data(loanService.createUserLoan(loan))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/disbursal/{id}")
    public ResponseEntity<Object> disbursalLoan(@PathVariable String id) {
        LoanResponse response = LoanResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Disbursal successfully")
                .data(loanService.disbursalLoan(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/end-loan/{id}")
    public ResponseEntity<Object> endLoan(@PathVariable String id) {
        LoanResponse response = LoanResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Loan ended")
                .data(loanService.endLoan(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
