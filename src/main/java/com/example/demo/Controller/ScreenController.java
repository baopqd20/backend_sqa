package com.example.demo.Controller;


import org.springframework.web.bind.annotation.GetMapping;

public interface ScreenController {

    @GetMapping("/login")
    public String home();

    @GetMapping("/createAccount")
    public String createAccount();
    @GetMapping("/createLoan")
    public String createLoan();
    @GetMapping("/information")
    public String getInformation();
}
