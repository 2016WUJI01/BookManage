package com.example.demo.service;

import com.example.demo.bean.Bookorder;
import com.example.demo.dao.BookorderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookorderServiceImpl implements BookorderService {
    @Autowired
    private BookorderDao bookorderDao;

    @Override
    public List<Bookorder> findAllBookorder() {
        return bookorderDao.findAllBookorder();
    }

    @Override
    public boolean addBookorder(Bookorder bookorder) {
        boolean flag = false;
        try {
            bookorderDao.addBookorder(bookorder);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteBookorder(Bookorder bookorder) {
        boolean flag = false;
        try {
            bookorderDao.deleteBookorder(bookorder);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateBookorder(Bookorder bookorder) {
        boolean flag = false;
        try {
            bookorderDao.updateBookorder(bookorder);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
