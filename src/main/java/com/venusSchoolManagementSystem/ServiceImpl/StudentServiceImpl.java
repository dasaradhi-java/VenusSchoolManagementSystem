package com.venusSchoolManagementSystem.ServiceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venusSchoolManagementSystem.Entity.Student;
import com.venusSchoolManagementSystem.Repository.StudentRepository;
import com.venusSchoolManagementSystem.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository repository;

	@Override
	public Student findStuById(long id) {
		return repository.findStudentById(id);
	}

	@Transactional
	public Student saveStudent(Student student) {
		Long maxRollNumber = getLatestRollNo();
		Long nextRollNumber = (maxRollNumber == null) ? 1 : maxRollNumber + 1;
		student.setRollNumber(nextRollNumber);
		return repository.save(student);

	}

	public Long getLatestRollNo() {
		Long maxRollNumber = repository.findTopByOrderByRollNumberDesc();
		return maxRollNumber;
	}

	public List<Student> findAllSudents() {
		return repository.findAll();
	}

	@Override
	public List<Student> findStuByClassName(String className) {
		return repository.findStuByClassName(className);
	}
	
	@Override
	public Student findStuByRollNo(Long rollNumber) {
		return repository.findStudentByRollNumber(rollNumber);
	}

	public Optional<Student> getStudentById(Long id) {
		return repository.findById(id);
	}

	public Student updateStudent(Long id, Student updatedStudent) {
		return repository.findById(id).map(existingStudent -> {
			// Update the fields
			existingStudent.setStudentName(updatedStudent.getStudentName());
			existingStudent.setParentMobile(updatedStudent.getParentMobile());
			existingStudent.setClassName(updatedStudent.getClassName());
			return repository.save(existingStudent); // Return the saved entity
		}).orElseThrow(() -> new RuntimeException("Student not found"));
	}

	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Student> findByRollNumber(Long rollNumber) {
		// TODO Auto-generated method stub
		return repository.findByRollNumber(rollNumber);

	}

}
