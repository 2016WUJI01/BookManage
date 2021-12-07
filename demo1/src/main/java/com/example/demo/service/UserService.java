package com.example.demo.service;

import com.example.demo.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<User> findAll();
}
