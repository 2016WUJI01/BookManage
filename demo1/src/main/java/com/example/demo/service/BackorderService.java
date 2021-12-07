package com.example.demo.service;

import com.example.demo.bean.Backorder;

import java.util.List;

public interface BackorderService {
    /**
     * find all backorder
     * @return
     */
    List<Backorder> findAllBackorder();
}
