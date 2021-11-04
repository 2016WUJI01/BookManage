package com.example.demo.service;

import com.example.demo.bean.Book;
import com.example.demo.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;

    @Override
    public boolean addBook(Book book) {
        boolean flag = false;
        try {
            bookDao.addBook(book);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateBook(Book book) {
        boolean flag = false;
        try {
            bookDao.updateBook(book);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteBook(int id) {
        boolean flag = false;
        try {
            bookDao.deleteBook(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Book findByBookname(String bookname) {
        return bookDao.findByBookname(bookname);
    }

    @Override
    public List<Book> findAllBook() {
        return bookDao.findAllBook();
    }
}
