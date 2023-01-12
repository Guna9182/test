package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;
import com.cg.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
		Employee newEmp = empService.addEmployee(emp);
		return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empList = empService.getAllEmployees();
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmp(@PathVariable("id") int id, @RequestBody Employee e) {
		try {
			Employee updatedEmp = empService.updateEmp(id, e);
			return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
		} catch (NoSuchEmployeeFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
		try {
			boolean deleted = empService.deleteEmployee(id);
			if (deleted) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (NoSuchEmployeeFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findempby/{id}")
	public ResponseEntity<Employee> findEmpById(@PathVariable("id") int id) {
		try {
			Employee emp = empService.findEmpById(id);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (NoSuchEmployeeFoundException ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employee/account/{accNo}")
	public ResponseEntity<Employee> getEmpDetailsByAccNo(@PathVariable("accNo") int accNo) {
		try {
			Employee emp = empService.getEmpDetailsByAccNo(accNo);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (NoSuchEmployeeFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}