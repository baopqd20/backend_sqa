package com.example.demo.Loan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    private final CustomerRepository customerRepository;

    public Loan createUserLoan(Loan request) {
        Date startDate = request.getStartDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, request.getLoanTerm());
        Date endDate = calendar.getTime();
        request.setEndDate(endDate);
        return loanRepository.save(request);
    }

    public List<Loan> getAllUserLoan(Long id) {
        Customer customer = customerRepository.getReferenceById(id);
        return loanRepository.findAllByCustomer(customer);
    }

    public Loan getDetailLoan(String id) {
        return loanRepository.findById(id).get();
    }

    @Transactional(rollbackFor = { Exception.class })
    public boolean deleteLoan(String id) {
        Loan loan = loanRepository.getReferenceById(id);
        loan.setStatus(-1);
        loanRepository.saveAndFlush(loan);
        return true;
    }

    @Transactional(rollbackFor = { Exception.class })
    public Loan disbursalLoan(String id) {
        Loan loan = loanRepository.getReferenceById(id);
        loan.setStatus(2);
        return loanRepository.saveAndFlush(loan);
    }

    @Transactional(rollbackFor = { Exception.class })
    public Loan endLoan(String id) {
        Loan loan = loanRepository.getReferenceById(id);
        loan.setStatus(3);
        return loanRepository.saveAndFlush(loan);
    }

    @Transactional(rollbackFor = { Exception.class })
    public boolean payLoan(Loan loan, Double money) {
        loan.setLoanAmount(loan.getLoanAmount() - money);
        loanRepository.saveAndFlush(loan);
        return true;
    }

    public List<Loan> getAllActiveLoan(Long id) {
        return loanRepository.findAllActiveLoan(id);
    }
}
