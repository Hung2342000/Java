package com.example.test_sql.repository;

import com.example.test_sql.model.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KhoaRepository extends JpaRepository<Khoa,String> {
}
