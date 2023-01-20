package com.exportImportexcel.controller;

import com.exportImportexcel.Utils;
import com.exportImportexcel.model.Employee;
import com.exportImportexcel.repository.EmployeeRepository;
import com.exportImportexcel.service.EmployeeExcelHelper;
import com.exportImportexcel.service.EmpolyeeService;
//import com.exportImportexcel.service.ExcelGenerorUtility;
import com.exportImportexcel.service.ExcelGenerorUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@CrossOrigin(value = "*", exposedHeaders = "X-Suggested-Filename")
@RestController
@RequestMapping("/api/excel")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmpolyeeService empolyeeService;


    public EmployeeController(EmployeeRepository employeeRepository, EmpolyeeService empolyeeService) {
        this.employeeRepository = employeeRepository;
        this.empolyeeService = empolyeeService;
    }




    @GetMapping("/employees")
    public List<Employee> getAll() {
        System.out.println("get all : " + empolyeeService.getAllEmployees());
        return empolyeeService.getAllEmployees();
    }

    @GetMapping("/download")
    public void getEmployeesDetailInExcel(HttpServletResponse response) throws IOException {

        String fileName = "employees_details_" + Utils.formatDate(new Date()) + ".xlsx";
        String fileType = "attachment;" + fileName;
        response.setHeader("X-Suggested-Filename", fileName);
        response.setHeader("Content-Disposition", fileType);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        ExcelGenerorUtility.getEmployeesDetailInExcel(response, employeeRepository.findAll());
        System.out.println(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadEmployeesFile(@RequestParam MultipartFile file) {
        var message = "";
        if (EmployeeExcelHelper.hasExcelFormat(file)) {
            try {
                empolyeeService.saveEmployee(file);
                message = "Uploaded the file commune successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body("OK");
            } catch (Exception e) {
                message = "Could not upload the commune file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("EXPECTATION_FAILED");
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST");
    }


}
