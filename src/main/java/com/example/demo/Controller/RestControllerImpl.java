package com.example.demo.Controller;

import com.example.demo.User.User;

import java.util.List;
@org.springframework.web.bind.annotation.RestController
public class RestControllerImpl implements RestController{
    @Override
    public List<User> doSearch(User obj) {
        return null;
    }

    @Override
    public User createdAcc(User obj) {
        return null;
    }

    @Override
    public Long checkLogin(User obj) {
        return null;
    }

    @Override
    public String checkRepeat(User obj) {
        return null;
    }

    @Override
    public User getInfoUser(User obj) {
        return null;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }
}
