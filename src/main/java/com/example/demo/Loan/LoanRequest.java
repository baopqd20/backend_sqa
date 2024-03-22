package com.example.demo.Loan;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanRequest {
    Long customer_id;
    Double loan_amount;
    Date start_date;
    Date end_date;
    Double loan_term;
}
