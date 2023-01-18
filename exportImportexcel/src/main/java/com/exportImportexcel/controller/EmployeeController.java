package com.exportImportexcel.controller;

import com.exportImportexcel.Utils;
import com.exportImportexcel.model.Employee;
import com.exportImportexcel.repository.EmployeeRepository;
import com.exportImportexcel.service.EmpolyeeService;
import com.exportImportexcel.service.ExcelGenerorUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmpolyeeService empolyeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmpolyeeService empolyeeService) {
        this.employeeRepository = employeeRepository;
        this.empolyeeService = empolyeeService;
    }

    @PostMapping("/excel")
    public static List<Employee> excelToTutorials(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheet("itRoad Employees");
            Iterator<Row> rows = sheet.iterator();

            List<Employee> tutorials = new ArrayList<Employee>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Employee tutorial = new Employee();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            tutorial.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            tutorial.setFirstName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            tutorial.setLastName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            tutorial.setBirthDay(currentCell.getDateCellValue());
                            break;

                        case 4:
                            tutorial.setSalary(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            tutorial.setStartsAt(currentCell.getDateCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }
                System.out.println(tutorials);
                tutorials.add(tutorial);
            }

            workbook.close();

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        System.out.println("get all : "+empolyeeService.getAllEmployees());
        return empolyeeService.getAllEmployees();
    }

    @GetMapping("/excel")
    public void getEmployeesDetailInExcel(HttpServletResponse response) throws IOException {

//        DateFormat dateFormat= new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
//        dateFormat.format(new Date())
        String fileType = "attachment; filename=employees_details_" + Utils.formatDate(new Date()) + ".xlsx";
        response.setHeader("Content-Disposition", fileType);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        ExcelGenerorUtility.getEmployeesDetailInExcel(response, employeeRepository.findAll());
    }
   /* public void postDataFromExcel(@RequestParam("file") MultipartFile file) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<Employee>();
            while (rows.hasNext()) {

                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        System.out.println(row.getCell(j));

                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

*/

}
