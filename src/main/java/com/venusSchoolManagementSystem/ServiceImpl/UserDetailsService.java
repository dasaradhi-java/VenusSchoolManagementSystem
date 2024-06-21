package com.venusSchoolManagementSystem.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venusSchoolManagementSystem.Entity.Student;
import com.venusSchoolManagementSystem.Entity.UserDetails;
import com.venusSchoolManagementSystem.Repository.UserDetailsRepository;
import com.venusSchoolManagementSystem.Service.StudentService;

@Component
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    
    @Autowired
    private StudentService studentService;
    
    public UserDetails saveUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails findByUserId(String userId) {
        return userDetailsRepository.findByUserId(userId);
    }
    
    public Student getStudentById(long studentId, UserDetails userDetails) throws Exception {
        if (userDetails.isAdmin()) {
            return studentService.findStuById(studentId);
        } else {
            throw new Exception("User is not authorized to perform this action");
        }
    }

    public Student saveStudent(Student student, UserDetails userDetails) throws Exception {
        if (userDetails.isAdmin()) {
            return studentService.saveStudent(student);
        } else {
            throw new Exception("User is not authorized to perform this action");
        }
    }
}
