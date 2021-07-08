package com.example.test_sql.service;

import com.example.test_sql.dto.KhoaDTO;
import com.example.test_sql.mapper.KhoaMapper;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.repository.KhoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class KhoaService {
    @Autowired
    public KhoaRepository khoaRepository;

    @Autowired
    public KhoaMapper khoaMapper;

    public List<KhoaDTO> getlist(){
        List<Khoa> khoaList = khoaRepository.findAll();
        List<KhoaDTO> khoaDTOList = new ArrayList<>();
        for (Khoa k: khoaList){
            KhoaDTO khoaDTO = khoaMapper.toDTO(k);
            khoaDTOList.add(khoaDTO);
        }
        return khoaDTOList;
    }

    public KhoaDTO get(String MaKhoa){
        Optional<Khoa> khoa = khoaRepository.findById(MaKhoa);
        if (khoa.isPresent()) {
            KhoaDTO khoaDTOget = new KhoaDTO();
            khoaDTOget = khoaMapper.toDTO(khoa.get());
            return khoaDTOget;
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi " + MaKhoa);
        }
    }

    public void Post(KhoaDTO khoaDTO){
        Optional<Khoa> khoaPost = khoaRepository.findById(khoaDTO.getMaKhoa());
        if (khoaPost.isPresent()){
            throw new RuntimeException("Bản ghi đã tồn tại");
        }
        else {
            Khoa khoa = khoaMapper.toKhoa(khoaDTO);
            khoaRepository.save(khoa);
        }
    }

    public void Put(KhoaDTO khoaDTO,String MaKhoa){
        Optional<Khoa> khoaUpdate = khoaRepository.findById(MaKhoa);
        if (khoaUpdate.isPresent()) {
            Khoa khoa = khoaMapper.toKhoa(khoaDTO);
            khoaUpdate.get().setTenKhoa(khoa.getTenKhoa());
            khoaUpdate.get().setDiaChi(khoa.getDiaChi());
            khoaRepository.save(khoaUpdate.get());
        }
        else {
            throw new RuntimeException("không tồn tại bản ghi");
        }
    }
    public void Delete(String MaKhoa){
        Optional<Khoa> khoaDelete = khoaRepository.findById(MaKhoa);
        if (khoaDelete.isPresent()) {
            khoaRepository.deleteById(MaKhoa);
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }

}
