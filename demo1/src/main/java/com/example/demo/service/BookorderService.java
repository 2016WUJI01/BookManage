package com.example.demo.service;

import com.example.demo.bean.Bookorder;

import java.util.List;

public interface BookorderService {
    List<Bookorder> findAllBookorder();

    boolean addBookorder(Bookorder bookorder);

    boolean deleteBookorder(Bookorder bookorder);

    boolean updateBookorder(Bookorder bookorder);
}
