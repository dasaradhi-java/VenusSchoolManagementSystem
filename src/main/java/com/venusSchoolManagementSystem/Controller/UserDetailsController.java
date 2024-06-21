package com.venusSchoolManagementSystem.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venusSchoolManagementSystem.Entity.UserDetails;
import com.venusSchoolManagementSystem.ServiceImpl.RoleService;
import com.venusSchoolManagementSystem.ServiceImpl.UserDetailsService;

@RestController
@RequestMapping("/users")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/saveUser")
    public ResponseEntity<UserDetails> saveUser(@RequestBody UserDetails userDetails) {
        try {
            UserDetails savedUser = userDetailsService.saveUserDetails(userDetails);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetails> getUser(@PathVariable String userId) {
        try {
            UserDetails user = userDetailsService.findByUserId(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
