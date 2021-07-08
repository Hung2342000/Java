package com.example.test_sql.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "khoa")
public class Khoa {
    @Id
    private String MaKhoa;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "TenKhoa")
    private String TenKhoa;

    @OneToMany(mappedBy = "MaKhoa", cascade = CascadeType.ALL )
    private List<Lop> lops;

}
