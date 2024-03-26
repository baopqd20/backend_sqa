package com.example.demo.Payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    Integer status;
    String message;
    Object data;
}
