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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Student;
import com.cg.exception.NoSuchCourseFoundException;
import com.cg.exception.NoSuchStudentFoundException;
import com.cg.service.StudentService;

@RestController
@RequestMapping("/studentcourse")
public class StudentController {

	@Autowired
	private StudentService stdService;

	@GetMapping("/getallstudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = stdService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@GetMapping("/getstudentbyid/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		try {
			Student student = stdService.getStudentById(id);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (NoSuchStudentFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addstudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student createdStudent = stdService.createStudent(student);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		try {
			Student updatedStudent = stdService.updateStudent(id, student);
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		} catch (NoSuchStudentFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		try {
			stdService.deleteStudent(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NoSuchStudentFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{studentId}/enroll/{courseId}")
	public ResponseEntity<Student> enrollStudentInCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
		try {
			Student student = stdService.enrollStudentInCourse(studentId, courseId);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (NoSuchStudentFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (NoSuchCourseFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{studentId}/drop/{courseId}")
	public ResponseEntity<Student> dropCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
		try {
			Student student = stdService.dropCourse(studentId, courseId);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (NoSuchStudentFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (NoSuchCourseFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
