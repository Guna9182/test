package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Course;
import com.cg.entity.Student;
import com.cg.exception.NoSuchCourseFoundException;
import com.cg.exception.NoSuchStudentFoundException;
import com.cg.repository.CourseRepo;
import com.cg.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo stdRepo;

	@Autowired
	private CourseRepo cRepo;

	@Override
	public List<Student> getAllStudents() {

		return stdRepo.findAll();
	}

	@Override
	public Student getStudentById(Long id) throws NoSuchStudentFoundException {

		Optional<Student> std = stdRepo.findById(id);
		if (std.isPresent()) {
			return std.get();
		} else {
			std.orElseThrow(() -> new NoSuchStudentFoundException("NoSuchStudentFound with id: " + id));
		}
		return null;

	}

	@Override
	public Student createStudent(Student student) {

		return stdRepo.save(student);
	}

	@Override
	public Student updateStudent(Long id, Student student) throws NoSuchStudentFoundException {

		Student std = stdRepo.findById(id)
				.orElseThrow(() -> new NoSuchStudentFoundException("NoSuchStudentFound with id: " + id));
		std.setFirstName(student.getFirstName());
		std.setLastName(student.getLastName());
		std.setEmail(student.getEmail());
		std.setCourses(student.getCourses());
		return std;
	}

	@Override
	public void deleteStudent(Long id) throws NoSuchStudentFoundException {
		Student std = stdRepo.findById(id)
				.orElseThrow(() -> new NoSuchStudentFoundException("NoSuchStudentFound with id: " + id));
		stdRepo.delete(std);
	}

	public Student enrollStudentInCourse(Long studentId, Long courseId)
			throws NoSuchStudentFoundException, NoSuchCourseFoundException {
		Student student = stdRepo.findById(studentId)
				.orElseThrow(() -> new NoSuchStudentFoundException("Student not found with id :" + studentId));
		Course course = cRepo.findById(courseId)
				.orElseThrow(() -> new NoSuchCourseFoundException("Course not found with id :" + courseId));
		student.getCourses().add(course);
		return stdRepo.save(student);
	}

	@Override
	public Student dropCourse(Long studentId, Long courseId)
			throws NoSuchStudentFoundException, NoSuchCourseFoundException {

		Student student = stdRepo.findById(studentId)
				.orElseThrow(() -> new NoSuchStudentFoundException("Student not found with id :" + studentId));
		Course course = student.getCourses().stream().filter(c -> c.getId().equals(courseId)).findFirst()
				.orElseThrow(() -> new NoSuchCourseFoundException("Course not found with id :" + courseId));

		student.getCourses().remove(course);
		return stdRepo.save(student);
	}

}
