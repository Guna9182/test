package com.cg.entity;

import javax.persistence.*;

@Entity
@Table(name = "coursesUD")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

//    @Column(name = "student_id")
//    private Long studentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//    public Long getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(Long studentId) {
//        this.studentId = studentId;
//    }

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
