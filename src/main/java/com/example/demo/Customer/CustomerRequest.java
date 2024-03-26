package com.example.demo.Customer;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CustomerRequest {
    @NotNull(message = "Name name is required")
    String name;
    @Min(value = 0, message = "Invalid gender")
    @Max(value = 1, message = "Invalid gender")
    Integer gender;
    String perAddress;
    String curAddress;
    String job;
    String position;
    Date dob;
    String nationality;
    String identify;
    String passport;
    String taxcode;
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])([0-9]{8})", message = "Phone number is not valid in Vietnam")
    private String phone_number;
    private String email;
}
