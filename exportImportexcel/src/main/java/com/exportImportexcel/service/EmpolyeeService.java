package com.exportImportexcel.service;

import com.exportImportexcel.model.Employee;
import com.exportImportexcel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpolyeeService {
    @Autowired
    private EmployeeRepository empolyeeService;

    public EmpolyeeService(EmployeeRepository empolyeeService) {
        this.empolyeeService = empolyeeService;

    }


    public List<Employee> getAllEmployees(){
        return empolyeeService.findAll();
    }
}
