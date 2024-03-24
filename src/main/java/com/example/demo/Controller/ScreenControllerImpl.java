package com.example.demo.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class ScreenControllerImpl implements ScreenController {

    @Override
    public  String home() { return "login.html";}

    @Override
    public String createAccount() {
        return "accountCreationScreen.html";
    }

    @Override
    public String createLoan() {
        return "createLoan.html";
    }

    @Override
    public String getInformation() {
        return "searchCustomer.html";
    }
}
