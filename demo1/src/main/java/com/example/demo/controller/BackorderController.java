package com.example.demo.controller;

import com.example.demo.bean.Backorder;
import com.example.demo.service.BackorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/backorder")
public class BackorderController {
    @Autowired
    private BackorderService backorderService;
    @RequestMapping(value = "/backorderAll",method = RequestMethod.GET)
    public List<Backorder> findAllBackorder() {
        System.out.println("查询所有退书");
        return backorderService.findAllBackorder();
    }
}
