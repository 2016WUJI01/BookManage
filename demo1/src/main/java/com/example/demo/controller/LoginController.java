package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean checkPassword(@RequestBody User user) {
        System.out.println("检查密码：");
        String username = user.getUsername();
        String password = user.getPassword();
        if(loginService.findUserByName(username) != null) {
            if(password.equals(loginService.getUserPassword(username))) {
                return true;
            }
        }
        return false;
    }
}
