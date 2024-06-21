package com.venusSchoolManagementSystem.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venusSchoolManagementSystem.Entity.UserDetails;
import com.venusSchoolManagementSystem.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UserDetails validateUser(String userId, String password) {
        UserDetails user = userRepository.findByUserId(userId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

//    public String getUserRole(UserDetails user) {
//        return user.getRole().getName();
//    }
}
