package com.venusSchoolManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.venusSchoolManagementSystem.Entity.Student;

public interface StudentService {
	
	public Student findStuById(long id);
	public Student saveStudent(Student student) ;
	public List<Student> findAllSudents();
	List<Student> findStuByClassName(String className);
	Optional<Student>  getStudentById(@PathVariable Long id);
	 Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent);
	 void deleteStudent(@PathVariable Long id);
	 public Student findStuByRollNo(Long rollNumber);
	 Optional<Student> findByRollNumber(Long rollNumber);
}
