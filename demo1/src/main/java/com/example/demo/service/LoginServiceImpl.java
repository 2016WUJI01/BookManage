package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;


    @Override
    public User findUserByName(String userName) {
        return userDao.findByName(userName);
    }

    @Override
    public String getUserPassword(String userName) {
        return userDao.getUserPassword(userName);
    }
}
