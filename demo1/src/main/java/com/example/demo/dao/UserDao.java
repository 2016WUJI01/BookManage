package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 增
     */
    @Insert("insert into user(id,username,password,nickname,sex,phonenumber,stuclass,position) values (#{id},#{username},#{password},#{nickname},#{sex},#{phonenumber},#{stuclass},#{position})")
    void addUser(User user);

    /**
     * 改
     */
    @Update("update user set username=#{username}, password={password} where id = #{id}")
    void updateUser(User user);

    /**
     * 删
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);

    /**
     * 根据名字查询数据
     */
    @Select("select id,username from user where username = #{username}")
    User findByName(@Param("userName") String userName);

    /**
     * 查询所有数据
     */
    @Select("select id,username,nickname,sex,phonenumber,stuclass,position from user")
    List<User> findAll();

    /**
     * 根据username查找密码
     *
     * @param userName
     * @return
     */
    @Select("select password from user where username = #{username}")
    String getUserPassword(@Param("userName") String userName);
}
