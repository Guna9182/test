package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

}
