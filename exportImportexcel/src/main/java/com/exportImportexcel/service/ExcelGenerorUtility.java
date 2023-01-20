package com.exportImportexcel.service;

import com.exportImportexcel.Utils;
import com.exportImportexcel.model.Employee;
import com.exportImportexcel.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelGenerorUtility {
    public static void getEmployeesDetailInExcel(HttpServletResponse response, List<Employee> employees) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("itRoad Employees");
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);

            //    HEADER
            Row row = sheet.createRow(0);

            Cell cell = row.createCell(0);
            cell.setCellValue("id");
            cell.setCellStyle(cellStyle);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue("First Name");
            cell1.setCellStyle(cellStyle);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue("Last Name");
            cell2.setCellStyle(cellStyle);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue("birthDay");
            cell3.setCellStyle(cellStyle);

            Cell cell4 = row.createCell(4);
            cell4.setCellValue("Salary");
            cell4.setCellStyle(cellStyle);

            Cell cell5 = row.createCell(5);
            cell5.setCellValue("start working at");
            cell5.setCellStyle(cellStyle);

            //Set data
            int rowNum = 1;
            for (Employee emp : employees) {
                Row empDataRow = sheet.createRow(rowNum++);

                Cell empIdCell = empDataRow.createCell(0);
                empIdCell.setCellStyle(cellStyle);
                empIdCell.setCellValue(emp.getId());

                Cell empFirstNameCell = empDataRow.createCell(1);
                empFirstNameCell.setCellStyle(cellStyle);
                empFirstNameCell.setCellValue(emp.getFirstName());

                Cell empLastNameCell = empDataRow.createCell(2);
                empLastNameCell.setCellStyle(cellStyle);
                empLastNameCell.setCellValue(emp.getLastName());

                Cell empBirthDayCell = empDataRow.createCell(3);
                empBirthDayCell.setCellStyle(cellStyle);
                empBirthDayCell.setCellValue(Utils.formatDate(emp.getBirthDay()));
                System.out.println(emp.getId());
                Cell empSalaryCell = empDataRow.createCell(4);
                empSalaryCell.setCellStyle(cellStyle);
                empSalaryCell.setCellValue(emp.getSalary());

                Cell empStartedCell = empDataRow.createCell(5);
                empStartedCell.setCellStyle(cellStyle);
                empStartedCell.setCellValue(Utils.formatDate(emp.getStartsAt()));


            }
            //write output to response
            workbook.write(response.getOutputStream());
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}

