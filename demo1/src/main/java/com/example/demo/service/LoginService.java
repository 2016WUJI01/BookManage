package com.example.demo.service;

import com.example.demo.bean.User;

public interface LoginService {
    /**
     * 根据名字查询信息
     * @param userName
     * @return
     */
    User findUserByName(String userName);

    /**
     * 根据username查找密码
     *
     * @param userName
     * @return
     */
    String getUserPassword(String userName);
}
