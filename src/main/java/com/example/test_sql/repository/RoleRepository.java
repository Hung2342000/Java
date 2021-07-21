package com.example.test_sql.repository;

import com.example.test_sql.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.name=?1")
    Role findByName(String name);
}
