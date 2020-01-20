package com.example.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TestController {
	@Autowired
	ObjectMapper mapper;
	@Autowired
	EmployeeRepo employeeRepo;

	@PostMapping
	@RequestMapping("/test")
	public ResponseEntity<String> addEmployee(@RequestBody String empdata) throws Exception {
		Employee readValue = mapper.readValue(empdata, Employee.class);
		Employee save = employeeRepo.save(readValue);
		String writeValueAsString = mapper.writeValueAsString(save);
		return new ResponseEntity<String>(writeValueAsString, HttpStatus.OK);
	}

	@GetMapping(path = "/getemp")
	public void getEmp() throws Exception {
		//Employee employee = new Employee("tarun", "address", 50000);
		//Employee save = employeeRepo.save(employee);
		List<Employee> findAll = employeeRepo.findAll();
		String writeValueAsString = mapper.writeValueAsString(findAll);
		System.out.println(writeValueAsString);
	}

	@PostMapping(path = "/upload")
	public void upload(@RequestParam("file") MultipartFile reapExcelDataFile) throws Exception {
		InputStream inputStream = reapExcelDataFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		List<Employee> employees = new ArrayList<Employee>();
		for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {

			XSSFRow row = worksheet.getRow(i);
			Employee  employee  = new Employee(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), 1000);
			employees.add(employee);
			System.out.println(row.getCell(0).getStringCellValue());
			System.out.println(row.getCell(1).getStringCellValue());
		}
		List<Employee> saveAll = employeeRepo.saveAll(employees);
		System.out.println(saveAll);
	}
}
