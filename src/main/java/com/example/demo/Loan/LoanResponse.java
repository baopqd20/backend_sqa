package com.example.demo.Loan;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanResponse {
    Integer status;
    String message;
    Object data;
}
