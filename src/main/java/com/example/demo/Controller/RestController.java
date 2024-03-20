package com.example.demo.Controller;

import com.example.demo.User.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface RestController {
    @RequestMapping(value = "/doSearch", method = RequestMethod.POST)
    public List<User> doSearch(@RequestBody User obj);

    @RequestMapping(value = "/createAcc", method = RequestMethod.POST)
    public User createdAcc(@RequestBody User obj);

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public Long checkLogin(@RequestBody User obj);

    @RequestMapping(value = "/checkRepeat", method = RequestMethod.POST)
    public String checkRepeat(@RequestBody User obj);

//    @RequestMapping(value = "/getHistory", method = RequestMethod.POST)
//    public List<HistoryBO> getHistory(@RequestBody User obj);

    @RequestMapping(value = "/getInfoUser", method = RequestMethod.POST)
    public User getInfoUser(@RequestBody User obj);


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean update(@RequestBody User obj);

//    @RequestMapping(value = "/removeHistory", method = RequestMethod.POST)
//    public boolean removeHistory(@RequestBody HistoryBO obj);

}
