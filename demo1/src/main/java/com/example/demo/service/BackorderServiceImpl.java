package com.example.demo.service;

import com.example.demo.bean.Backorder;
import com.example.demo.dao.BackorderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackorderServiceImpl implements BackorderService {
    @Autowired
    private BackorderDao backorderDao;

    @Override
    public List<Backorder> findAllBackorder() {
        return backorderDao.findAllBackorder();
    }
}
