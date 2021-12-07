package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser")
    public boolean addUser(@RequestBody User user) {
        System.out.println("新增数据：");
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        System.out.println("更新数据：");
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean deleteUser(@RequestParam(value = "id", required = true) int Id) {
        System.out.println("删除数据：");
        return userService.deleteUser(Id);
    }


    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public List<User> findAll() {
        System.out.println("查询所有数据：");
        return userService.findAll();
    }

}
