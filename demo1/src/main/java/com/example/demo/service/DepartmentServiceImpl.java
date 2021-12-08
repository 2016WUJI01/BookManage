package com.example.demo.service;

import com.example.demo.bean.Department;
import com.example.demo.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findAllDepartment() {
        return departmentDao.findAllDepartment();
    }
}
