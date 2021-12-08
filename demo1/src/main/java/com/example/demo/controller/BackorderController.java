package com.example.demo.controller;

import com.example.demo.bean.Backorder;
import com.example.demo.service.BackorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/backorder")
public class BackorderController {
    @Autowired
    private BackorderService backorderService;

    @RequestMapping(value = "/backorderAll", method = RequestMethod.GET)
    public List<Backorder> findAllBackorder() {
        System.out.println("查询所有退书");
        return backorderService.findAllBackorder();
    }

    @RequestMapping(value = "/backorder", method = RequestMethod.POST)
    public boolean addBackorder(@RequestBody Backorder backorder) {
        System.out.println("新增订单：");
        return backorderService.addBackorder(backorder);
    }

    @RequestMapping(value = "/backorder", method = RequestMethod.PUT)
    public boolean updateBackorder(@RequestBody Backorder backorder) {
        System.out.println("修改订单：");
        return backorderService.updateBackorder(backorder);
    }

    @RequestMapping(value = "/backorderDelete", method = RequestMethod.POST)
    public boolean deleteBackorder(@RequestBody Backorder backorder) {
        System.out.println("删除订单：");
        return backorderService.deleteBackorder(backorder);
    }
}
