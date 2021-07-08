package com.example.test_sql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "baithi")

public class Baithi {
    @Id
    private String MaBaiThi;

    @ManyToOne
    @JoinColumn(name = "MaMonHoc",nullable = false)
    private Monhoc MaMonHoc;

    @ManyToOne
    @JoinColumn(name = "MaSinhVien",nullable = false)
    private Sinhvien MaSinhVien;

}
