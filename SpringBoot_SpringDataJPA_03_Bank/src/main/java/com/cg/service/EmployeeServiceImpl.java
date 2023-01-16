package com.cg.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;
import com.cg.repository.EmployeeRepo;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo empRepo;

	public EmployeeServiceImpl(EmployeeRepo empRepo2) {
		this.empRepo = empRepo;
	}

	@Override
	public Employee addEmployee(Employee emp) {

		return empRepo.save(emp);
	}
	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}
	@Override
	@Transactional
	public Employee updateEmp(int id, Employee e) throws NoSuchEmployeeFoundException {
		Employee emp = findEmpById(id);
		emp.setEname(e.getEname());
		emp.setEsal(e.getEsal());
		emp.setEaccount(e.getEaccount());
		return emp;
	}
	@Override
	@Transactional
	public boolean deleteEmployee(int id) throws NoSuchEmployeeFoundException {
		empRepo.deleteById(id);
		Optional<Employee> emp = empRepo.findById(id);
		return !(emp.isPresent());
	}
	@Override
	public Employee findEmpById(int id) throws NoSuchEmployeeFoundException {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp.isPresent())
			return emp.get();
		else
			throw new NoSuchEmployeeFoundException("Employee not found with id " + id);
	}
	@Override
	public Employee getEmpDetailsByAccNo(int accNo) throws NoSuchEmployeeFoundException {
		return empRepo.findByEaccountAccNo(accNo);
	}
}