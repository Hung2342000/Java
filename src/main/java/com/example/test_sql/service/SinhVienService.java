package com.example.test_sql.service;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.mapper.SinhVienMapper;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Lop;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.CustomeRepository;
import com.example.test_sql.repository.LopRepository;
import com.example.test_sql.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SinhVienService {
    @Autowired
    LopRepository lopRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    SinhVienMapper sinhVienMapper;

    @Autowired
    CustomeRepository customeRepository;

    public List<SinhVienDTO> getList(Pageable pageable){
        List<Sinhvien> sinhviens = sinhVienRepository.findAll(pageable).toList();
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        for (Sinhvien sv: sinhviens) {
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sv);
            sinhVienDTOS.add(sinhVienDTO);

        }
        return sinhVienDTOS;
    }

    public List<SinhVienDTO> List(){
        List<Sinhvien> sinhviens = sinhVienRepository.findAll();
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        for (Sinhvien sv: sinhviens) {
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sv);
            sinhVienDTOS.add(sinhVienDTO);
        }
        return sinhVienDTOS;
    }

    public SinhVienDTO get(String masinhvien){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(masinhvien);
        SinhVienDTO sinhVienDTO = null;
        if (sinhvien.isPresent()){
            sinhVienDTO = sinhVienMapper.toSVDTO(sinhvien.get());
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
        return sinhVienDTO;
    }
    public List<Object> getLists(){
        List<Object> sinhviens = Collections.singletonList(sinhVienRepository.findAll());
        return sinhviens;

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
            Optional<Lop> lop = lopRepository.findById(sinhVienDTO.getMaLop());
            Sinhvien sinhvienput = sinhVienMapper.toSV(sinhVienDTO);
            sinhvienput.setMaLop(lop.get());
            sinhVienRepository.save(sinhvienput);
            return sinhVienDTO;
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
