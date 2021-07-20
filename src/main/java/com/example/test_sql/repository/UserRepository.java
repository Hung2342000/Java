package com.example.test_sql.repository;

import com.example.test_sql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query( "select u from User u where u.email = ?1")
    User findByEmail(String email);
}
