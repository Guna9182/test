package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	@Query("SELECT e FROM Employee e JOIN e.eaccount a WHERE a.accNo = :accNo")
    Employee findByEaccountAccNo(@Param("accNo") int accNo);
}