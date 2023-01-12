package com.cg.service;

import java.util.List;

import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;

public interface EmployeeService {

	Employee addEmployee(Employee emp);

	List<Employee> getAllEmployees();

	Employee findEmpById(int id) throws NoSuchEmployeeFoundException;;

	Employee updateEmp(int id, Employee e) throws NoSuchEmployeeFoundException;

	boolean deleteEmployee(int id) throws NoSuchEmployeeFoundException;

	Employee getEmpDetailsByAccNo(int accNo) throws NoSuchEmployeeFoundException;
}