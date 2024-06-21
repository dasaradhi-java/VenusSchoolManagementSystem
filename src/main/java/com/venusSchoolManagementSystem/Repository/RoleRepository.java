package com.venusSchoolManagementSystem.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venusSchoolManagementSystem.Entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
