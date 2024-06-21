package com.venusSchoolManagementSystem.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venusSchoolManagementSystem.Entity.LoginRequest;
import com.venusSchoolManagementSystem.Entity.UserDetails;
import com.venusSchoolManagementSystem.ServiceImpl.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDetails loginRequest) {
        UserDetails user = userService.validateUser(loginRequest.getUserId(), loginRequest.getPassword());

        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("role", String.valueOf(user.getRole()));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    
}
