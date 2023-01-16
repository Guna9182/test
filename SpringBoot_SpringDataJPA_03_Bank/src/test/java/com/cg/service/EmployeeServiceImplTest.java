package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.entity.Account;
import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;
import com.cg.repository.EmployeeRepo;

class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepo empRepo;

	@InjectMocks
	private EmployeeServiceImpl eService;

	@BeforeEach
	public void setUp() {
		// initialize mocks
		eService = new EmployeeServiceImpl(empRepo);
		MockitoAnnotations.openMocks(this);
	}

	// test method for adding an employee

	@Test
	public void testAddEmployee() {

		Account acc = new Account();
		acc.setAccNo(91824);
		acc.setBankName("icici");
		acc.setAccBal(5000);

		Employee emp = new Employee();
		emp.setEid(1);
		emp.setEname("Guna");
		emp.setEsal(20000);
		emp.setEaccount(acc);

		// Here Saving the object to the mocked object
		when(empRepo.save(emp)).thenReturn(emp);

		// Here we have to check the actual method
		Employee savedEmp = eService.addEmployee(emp);

		assertEquals(savedEmp, emp);
	}

	// test method for displaying the employeeList

	@Test
	public void testGetAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		Account acc = new Account();
		acc.setAccNo(91824);
		acc.setBankName("icici");
		acc.setAccBal(5000);

		Employee emp = new Employee();
		emp.setEid(1);
		emp.setEname("Guna");
		emp.setEsal(20000);
		emp.setEaccount(acc);

		Account acc2 = new Account();
		acc.setAccNo(12345);
		acc.setBankName("sbi");
		acc.setAccBal(500);

		Employee emp2 = new Employee();
		emp.setEid(2);
		emp.setEname("Raki");
		emp.setEsal(100000);
		emp.setEaccount(acc2);
		empList.add(emp);
		empList.add(emp2);

		when(empRepo.findAll()).thenReturn(empList);
		assertEquals(eService.getAllEmployees(), empList);
	}

	// test the findEmpById method

	@Test
	public void testFindEmpById() throws NoSuchEmployeeFoundException {
		Account acc = new Account();
		acc.setAccNo(91824);
		acc.setBankName("icici");
		acc.setAccBal(5000);

		Employee emp = new Employee();
		emp.setEid(1);
		emp.setEname("Guna");
		emp.setEsal(20000);
		emp.setEaccount(acc);

		when(empRepo.findById(1)).thenReturn(Optional.of(emp));

		assertEquals(eService.findEmpById(1), emp);

	}

	// test the findEmpById exception case

	@Test
	public void testFindEmpByIdException() {
		when(empRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(NoSuchEmployeeFoundException.class,()->{
		eService.findEmpById(1);
		});
	}

}