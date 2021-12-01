package com.example.demo.dao;

import com.example.demo.bean.Stuclass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuclassDao {
    /**
     * add a new student class
     * @param stuclass
     */
    @Insert("insert into stuclass(stuclass,college,classname) values (#{stuclass},#{college},#{classname})")
    void addStuclass(Stuclass stuclass);
}
