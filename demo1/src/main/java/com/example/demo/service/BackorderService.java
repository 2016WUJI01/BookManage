package com.example.demo.service;

import com.example.demo.bean.Backorder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BackorderService {
    /**
     * find all backorder
     *
     * @return
     */
    List<Backorder> findAllBackorder();

    boolean addBackorder(Backorder backorder);

    boolean deleteBackorder(Backorder backorder);

    boolean updateBackorder(Backorder backorder);
}
