package com.example.demo.controller;

import com.example.demo.bean.Book;
import com.example.demo.bean.User;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public boolean addBook(@RequestBody Book book) {
        System.out.println("新增书籍：");
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public boolean updateBook(@RequestBody Book book) {
        System.out.println("更新书籍：");
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "/bookDelete", method = RequestMethod.POST)
    public boolean deleteBook(@RequestBody Book book) {
        System.out.println("删除数据：");
        return bookService.deleteBook(book);
    }

    @RequestMapping(value = "/bookAll", method = RequestMethod.GET)
    public List<Book> findAllBook() {
        System.out.println("查询所有书籍：");
        return bookService.findAllBook();
    }


}
