package com.venusSchoolManagementSystem.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VenusStudentDetails")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rollNumber;
	private String studentName;
	private String parentMobile;
	private String className;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Long rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
