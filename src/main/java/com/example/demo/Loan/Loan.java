package com.example.demo.Loan;

import java.util.Date;
import java.util.List;

import com.example.demo.Customer.Customer;
import com.example.demo.Payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id",nullable = false)
    private Customer customer;
    @Column(name = "loan_amount")
    private Double loanAmount;
    @Column(name = "interest_rate")
    private Double interestRate;
    @Column(name = "loan_term")
    private Integer loanTerm;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status")
    private Integer status;
    @Column(name = "has_salary_table")
    private Integer hasSalaryTable;
    @Column(name = "has_salary_statement")
    private Integer hasSalaryStatement;
    @Column(name = "has_collateral")
    private Integer hasCollateral;
    @JsonIgnore
    @OneToMany(mappedBy = "loan")
    private List<Payment> payments;
}
