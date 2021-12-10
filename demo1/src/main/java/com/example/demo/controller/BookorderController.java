package com.example.demo.controller;

import com.example.demo.bean.Bookorder;
import com.example.demo.service.BookorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bookorder")
public class BookorderController {
    @Autowired
    private BookorderService bookorderService;

    @RequestMapping(value = "/bookorderAll", method = RequestMethod.GET)
    public List<Bookorder> findAllBookorder() {
        System.out.println("查询所有订单");
        return bookorderService.findAllBookorder();
    }

    @RequestMapping(value = "/bookorder", method = RequestMethod.POST)
    public boolean addBookorder(@RequestBody Bookorder bookorder) {
        System.out.println("新增订单：");
        return bookorderService.addBookorder(bookorder);
    }

    @RequestMapping(value = "/bookorder", method = RequestMethod.PUT)
    public boolean updateBookorder(@RequestBody Bookorder bookorder) {
        System.out.println("修改订单：");
        return bookorderService.updateBookorder(bookorder);
    }

    @RequestMapping(value = "/bookorderDelete", method = RequestMethod.POST)
    public boolean deleteBookorder(@RequestBody Bookorder bookorder) {
        System.out.println("删除订单：");
        return bookorderService.deleteBookorder(bookorder);
    }
}
