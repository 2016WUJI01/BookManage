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

    @Override
    public boolean addBackorder(Backorder backorder) {
        boolean flag = false;
        try {
            backorderDao.addBackorder(backorder);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteBackorder(Backorder backorder) {
        boolean flag = false;
        try {
            backorderDao.deleteBackorder(backorder);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateBackorder(Backorder backorder) {
        boolean flag = false;
        try {
            backorderDao.updateBackorder(backorder);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
