package com.example.demo.dao;

import com.example.demo.bean.Backorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BackorderDao {
    /**
     * find all backorder
     * @return
     */
    @Select("select * from backorder")
    List<Backorder> findAllBackorder();
}
