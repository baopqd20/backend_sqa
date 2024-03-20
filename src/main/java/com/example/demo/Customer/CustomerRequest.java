package com.example.demo.Customer;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CustomerRequest {
    @NotNull(message = "Full name is required")
    String full_name;
    @Size(min = 0,max = 1, message = "Select the right gender")
    Integer gender;
    String perAddress;
    String curAddress;
    String job;
    String position;
    Date dob;
    String nationality;
    @Size(min = 12, max = 12, message = "Identify number must be 12 characters")
    String identify;
    @Pattern(regexp = "^(?!^0+$)[a-zA-Z0-9]{3,20}", message = "Passport is not valid")
    String passport;
    @Pattern(regexp = "^([0-9]{10})(|)(-[0-9]{3}|)", message = "Tax code is not valid")
    String taxcode;
    @Pattern(regexp = "/(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/", message = "Phone number is not valid in Vietnam")
    private String phone_number;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Email is not valid")
    private String email;
}
