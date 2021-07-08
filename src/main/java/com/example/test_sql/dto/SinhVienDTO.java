package com.example.test_sql.dto;

import com.example.test_sql.model.Lop;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class SinhVienDTO {

    private String MaSinhVien;
    private java.util.Date Date;
    private String TenSV;
    private String DiaChi;
    private String MaLop;
}
