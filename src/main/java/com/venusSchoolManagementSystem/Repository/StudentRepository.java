package com.venusSchoolManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.venusSchoolManagementSystem.Entity.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long>{	
		Student findStudentById(long id); 
		List<Student> findAll();
		Student save(Student student);
	    @Query("SELECT COALESCE(MAX(s.rollNumber), 0) FROM Student s")
	    Long findTopByOrderByRollNumberDesc();
	    @Query("SELECT s FROM Student s WHERE s.className = :className")
	    List<Student> findStuByClassName(@Param("className") String className);
	   

	

}
