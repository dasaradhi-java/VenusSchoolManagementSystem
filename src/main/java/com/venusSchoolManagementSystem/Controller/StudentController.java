package com.venusSchoolManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venusSchoolManagementSystem.Entity.Student;
import com.venusSchoolManagementSystem.Service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService service;

	
	@GetMapping("/findStudentById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable long id) {
		try {
			Student	findStuById = service.findStuById(id);
			return ResponseEntity.ok(findStuById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	  @PostMapping("/api/studentregister")
	    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
	        try {
	            Student registeredStudent = service.saveStudent(student);
	            return ResponseEntity.ok(registeredStudent);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	@GetMapping("/GetAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		try {
			List<Student> findStuById = service.findAllSudents();
			return ResponseEntity.ok(findStuById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	

	 @GetMapping("/class/{className}")
	    public ResponseEntity<List<Student>> getStudentsByClassName(@PathVariable String className) {
	        try {
	            List<Student> students = service.findStuByClassName(className);
	            return ResponseEntity.ok(students);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	 @DeleteMapping("/api/deleteStudent/{id}")
	 public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	     try {
	         service.deleteStudent(id);
	         return ResponseEntity.noContent().build();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	     
	 }
	 @PutMapping("/api/studentregister/{id}")
	 public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
	     try {
	         Student student = service.updateStudent(id, updatedStudent);
	         return ResponseEntity.ok(student);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	 }
	 @GetMapping("api/student/{id}")
	 public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	     Optional<Student> student = service.getStudentById(id);
	     return student.map(ResponseEntity::ok)
	                   .orElse(ResponseEntity.notFound().build());
	 }

	 @GetMapping("api/studentByRollNumber/{rollNumber}")
	 public ResponseEntity<Student> getStudentByRollNumber(@PathVariable Long rollNumber) {
	   Student student = service.findStuByRollNo(rollNumber);
	     return ResponseEntity.ok(student);
	 }
	 @PutMapping("/updateStudent/{rollNumber}")
	    public ResponseEntity<Student> updateStudents(@PathVariable("rollNumber") Long rollNumber, @RequestBody Student studentDetails) {
	        Optional<Student> studentOptional = service.findByRollNumber(rollNumber);

	        if (studentOptional.isPresent()) {
	            Student student = studentOptional.get();
	            student.setStudentName(studentDetails.getStudentName());
	            student.setParentMobile(studentDetails.getParentMobile());
	            student.setClassName(studentDetails.getClassName());

	            // Save the updated student record
	            Student updatedStudent = service.saveStudent(student);
	            return ResponseEntity.ok(updatedStudent);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
