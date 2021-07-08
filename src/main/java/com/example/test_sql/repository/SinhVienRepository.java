package com.example.test_sql.repository;

import com.example.test_sql.model.Sinhvien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinhVienRepository extends JpaRepository<Sinhvien,String> {
}
