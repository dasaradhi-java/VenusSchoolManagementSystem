package com.venusSchoolManagementSystem.Repository;

import com.venusSchoolManagementSystem.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {
    UserDetails findByUserId(String userId);
}
