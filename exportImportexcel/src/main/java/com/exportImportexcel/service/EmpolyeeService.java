package com.exportImportexcel.service;

import com.exportImportexcel.model.Employee;
import com.exportImportexcel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@Slf4j
public class EmpolyeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmpolyeeService(EmployeeRepository empolyeeService) {
        this.employeeRepository = empolyeeService;
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public void saveEmployee(MultipartFile file) {
        try {
            List<Employee> communes = EmployeeExcelHelper.excelToCommunes(file.getInputStream());
            StopWatch watch = new StopWatch();
            watch.start();
            employeeRepository.saveAll(communes);
            watch.stop();
            log.info("Save communes {} time Elapsed: {}", communes.size(), watch.getTotalTimeSeconds());
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
