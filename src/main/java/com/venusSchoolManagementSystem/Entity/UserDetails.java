package com.venusSchoolManagementSystem.Entity;

import javax.persistence.*;

@Entity
@Table(name = "UserDetails")
public class UserDetails {

    @Id
    private String userId;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    // Constructors
    public UserDetails() {}

    public UserDetails(String userId, String password, Roles role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return role != null && role.getName().equalsIgnoreCase("ADMIN");
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
