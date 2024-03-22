package com.example.demo.Payment;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    @NotNull(message = "Please enter your amount of money")
    Double amount;
}
