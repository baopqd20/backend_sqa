package com.example.demo.Advices;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    Integer status;
    Object message;
}
