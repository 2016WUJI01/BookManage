package com.example.demo.controller;

import com.example.demo.bean.Department;
import com.example.demo.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentDao departmentDao;
    @RequestMapping(value = "/departmentAll",method = RequestMethod.GET)
    public List<Department> findAllDepartment(){
        System.out.println("查询所有院系：");
        return departmentDao.findAllDepartment();
    }
}
