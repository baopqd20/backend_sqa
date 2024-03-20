package com.example.demo.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CustomerResponse {
    Integer status;
    String message;
    Object data;    
}
