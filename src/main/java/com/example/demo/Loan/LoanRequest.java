package com.example.demo.Loan;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanRequest {
    Double interest_rate;
    Long customer_id;
    Double loan_amount;
    Date start_date;
    Integer loan_term;
    Integer has_salary_table;
    Integer has_salary_statement;
    Integer has_collateral;
}
