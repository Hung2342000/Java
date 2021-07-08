package com.example.test_sql.service;

import com.example.test_sql.dto.BaiThiDTO;
import com.example.test_sql.mapper.BaiThiMapper;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Monhoc;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.BaiThiRepository;
import com.example.test_sql.repository.MonHocRepository;
import com.example.test_sql.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BaiThiService {
    @Autowired
    BaiThiMapper baiThiMapper;

    @Autowired
    BaiThiRepository baiThiRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    MonHocRepository monHocRepository;

    public List<BaiThiDTO> list(){
        List<Baithi> baithis = baiThiRepository.findAll();
        List<BaiThiDTO> baiThiDTOList = new ArrayList<BaiThiDTO>();
        for (Baithi baithi: baithis) {
            BaiThiDTO baiThiDTO = baiThiMapper.baiThiDTO(baithi);
            baiThiDTOList.add(baiThiDTO);

        }
        return baiThiDTOList;
    }

    public BaiThiDTO get(String mabaithi){
        Optional<Baithi> baithi = baiThiRepository.findById(mabaithi);
        if(baithi.isPresent()){
            BaiThiDTO baiThiDTO = baiThiMapper.baiThiDTO(baithi.get());
            return baiThiDTO;
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }

    public void  post(BaiThiDTO baiThiDTO){
        Optional<Baithi> baithi = baiThiRepository.findById(baiThiDTO.getMaBaiThi());
        if (baithi.isPresent()) {
            throw new RuntimeException("Đã tồn tại bản ghi");
        }
        else {
            Optional<Sinhvien> sinhvien = sinhVienRepository.findById(baiThiDTO.getMaSinhVien());
            Optional<Monhoc> monhoc = monHocRepository.findById(baiThiDTO.getMaMonHoc());
            Baithi baithi1 = baiThiMapper.baithi(baiThiDTO);
            baithi1.setMaMonHoc(monhoc.get());
            baithi1.setMaSinhVien(sinhvien.get());
            baiThiRepository.save(baithi1);
        }
    }

    public void put(BaiThiDTO baiThiDTO , String mabaithi){
        Optional<Baithi> baithi = baiThiRepository.findById(mabaithi);
        if(baithi.isPresent()){
            Optional<Sinhvien> sinhvien = sinhVienRepository.findById(baiThiDTO.getMaSinhVien());
            Optional<Monhoc> monhoc = monHocRepository.findById(baiThiDTO.getMaMonHoc());
            Baithi baithiput = baiThiMapper.baithi(baiThiDTO);
            baithiput.setMaSinhVien(sinhvien.get());
            baithiput.setMaMonHoc(monhoc.get());
            baithi.get().setDiem(baithiput.getDiem());
            baithi.get().setMaMonHoc(baithiput.getMaMonHoc());
            baithi.get().setMaSinhVien(baithiput.getMaSinhVien());
            baiThiRepository.save(baithi.get());


        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }

    public void delete(String mabaithi){
        Optional<Baithi> baithi = baiThiRepository.findById(mabaithi);
        if(baithi.isPresent()){
            baiThiRepository.delete(baithi.get());
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }
}
