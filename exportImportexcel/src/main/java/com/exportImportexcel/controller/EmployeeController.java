package com.exportImportexcel.controller;

import com.exportImportexcel.Utils;
import com.exportImportexcel.repository.EmployeeRepository;
import com.exportImportexcel.service.ExcelGenerorUtility;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class EmployeeController {
private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/excel")
    public void getEmployeesDetailInExcel(HttpServletResponse response) throws IOException {

//        DateFormat dateFormat= new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
//        dateFormat.format(new Date())
        System.out.println("in side the getE "+new Date());
        String fileType="attachment; filename=employees_details_"+ Utils.formatDate(new Date())+".xlsx";
        response.setHeader("Content-Disposition",fileType);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        ExcelGenerorUtility.getEmployeesDetailInExcel(response,employeeRepository.findAll());
    }

}
