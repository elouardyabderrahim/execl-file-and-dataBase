package com.exportImportexcel.service;

import com.exportImportexcel.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "itRoad Employees";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Employee> excelToCommunes(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            System.out.println("inside try");

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<Employee>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                System.out.println("inside while");

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    System.out.println("verifying rowNumber " + rowNumber);

                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Employee employee = new Employee();

                int cellIdx = 1;
                while (cellsInRow.hasNext()) {
                    System.out.println("inside while hasNext()");

                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {

                        // TODO create an api to download a file for use to fill.

                        /*
                        //Don't read the Id Cell .
                        case 0:
                            System.out.println("rows.hasNext() case0");
                            System.out.println(currentCell.getNumericCellValue());

                            tutorial.setId((long) (currentCell.getNumericCellValue()));

                            break;*/

                        case 1:
                            System.out.println("FirstName case1 :" + currentCell.getStringCellValue());


                            employee.setFirstName(currentCell.getStringCellValue());

                            break;

                        case 2:

                            System.out.println("LastName case 2 :" + currentCell.getStringCellValue());

                            employee.setLastName(currentCell.getStringCellValue());

                            break;

                        case 3:

                            System.out.println("birthday case 3 :" + currentCell.getDateCellValue());

                            employee.setBirthDay(currentCell.getDateCellValue());

                            break;
                        case 4:

                            System.out.println("Salary case 4 :" + currentCell.getNumericCellValue());
                            employee.setSalary(currentCell.getNumericCellValue());

                            break;

                        case 5:

                            System.out.println("StartsAt case 5 : " + currentCell.getDateCellValue());
                            employee.setStartsAt(currentCell.getDateCellValue());


                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }
                System.out.println("employee : "+ employee);

                employees.add(employee);
            }

            workbook.close();
            System.out.println("employees : "+employees);
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

