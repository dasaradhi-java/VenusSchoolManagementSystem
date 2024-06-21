package com.venusSchoolManagementSystem.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venusSchoolManagementSystem.Entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
//    UserDetails findByUsename(String userId);
  UserDetails findByUserId(String userId);


}
