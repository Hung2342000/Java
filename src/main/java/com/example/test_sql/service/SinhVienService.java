package com.example.test_sql.service;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.mapper.SinhVienMapper;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Lop;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.LopRepository;
import com.example.test_sql.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SinhVienService {
    @Autowired
    LopRepository lopRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    SinhVienMapper sinhVienMapper;

    public List<SinhVienDTO> getList(Pageable pageable){
        List<Sinhvien> sinhviens = sinhVienRepository.findAll(pageable).toList();
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        for (Sinhvien sv: sinhviens) {
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sv);
            sinhVienDTOS.add(sinhVienDTO);

        }
        return sinhVienDTOS;
    }
    public SinhVienDTO get(String masinhvien){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(masinhvien);
        if (sinhvien.isPresent()){
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sinhvien.get());
            return sinhVienDTO;
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }

    public SinhVienDTO post(SinhVienDTO sinhVienDTO){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(sinhVienDTO.getMaSinhVien());
        if(sinhvien.isPresent()){
            throw new RuntimeException("Đã tồn tại bản ghi");

        }
        else {
            Optional<Lop> lop = lopRepository.findById(sinhVienDTO.getMaLop());
            Sinhvien sinhvienpost = sinhVienMapper.toSV(sinhVienDTO);
            sinhvienpost.setMaLop(lop.get());
            sinhVienRepository.save(sinhvienpost);
            return  sinhVienDTO;
        }
    }

    public SinhVienDTO put(SinhVienDTO sinhVienDTO){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(sinhVienDTO.getMaSinhVien());
        if(sinhvien.isPresent()){
            Optional<Lop> lop = lopRepository.findById(sinhVienDTO.getMaLop());
            Sinhvien sinhvienput = sinhVienMapper.toSV(sinhVienDTO);
            sinhvien.get().setMaLop(lop.get());
            sinhvien.get().setMaSinhVien(sinhvienput.getMaSinhVien());
            sinhvien.get().setDiaChi(sinhvienput.getDiaChi());
            sinhvien.get().setDate(sinhvienput.getDate());
            sinhvien.get().setTenSV(sinhvienput.getTenSV());
            sinhVienRepository.save(sinhvien.get());
            return sinhVienDTO;

        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }

    public void delete(String maisinhvien){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(maisinhvien);
        List<Baithi> baithis = sinhvien.get().getBaithis();
        if (sinhvien.isPresent() && baithis.size()<=0){
            sinhVienRepository.delete(sinhvien.get());
        }
        else {
            throw new RuntimeException("Không hợp lệ");
        }
    }




}
