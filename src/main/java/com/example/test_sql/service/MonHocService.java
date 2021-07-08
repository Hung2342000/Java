package com.example.test_sql.service;

import com.example.test_sql.dto.MonHocDTO;
import com.example.test_sql.mapper.MonHocMapper;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.model.Monhoc;
import com.example.test_sql.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MonHocService {
    @Autowired
    MonHocRepository monHocRepository ;

    @Autowired
    MonHocMapper monHocMapper;

    public List<MonHocDTO> getList(){
        List<Monhoc> monhocList = monHocRepository.findAll();
        List<MonHocDTO> monHocDTOList = new ArrayList<>();
        for (Monhoc monhoc : monhocList) {
            MonHocDTO monHocDTO = monHocMapper.monHocDTO(monhoc);
            monHocDTOList.add(monHocDTO);
        }
        return monHocDTOList;
    }

    public MonHocDTO get(String MaMonhoc){
        Optional<Monhoc> monhoc = monHocRepository.findById(MaMonhoc);
        if(monhoc.isPresent()){
            MonHocDTO monHocDTO = monHocMapper.monHocDTO(monhoc.get());
            return monHocDTO;
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi " + MaMonhoc);
        }
    }

    public void post(MonHocDTO monHocDTO){
        Optional<Monhoc> monhocPost = monHocRepository.findById(monHocDTO.getMaMonHoc());
        if(monhocPost.isPresent()){
            throw new RuntimeException("Đã tồn tại bản ghi " );
        }
        else{
            Monhoc monhoc = monHocMapper.monhoc(monHocDTO);
            monHocRepository.save(monhoc);
        }
    }

    public void put(MonHocDTO monHocDTO, String MaMonhoc){
        Optional<Monhoc> monhocPut = monHocRepository.findById(MaMonhoc);
        if (monhocPut.isPresent()){
            Monhoc monhoc = monHocMapper.monhoc(monHocDTO);
            monhocPut.get().setTenMonHoc(monhoc.getTenMonHoc());
            monHocRepository.save(monhocPut.get());
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi " + MaMonhoc);
        }
    }

    public void delete(String MaMonhoc){
        Optional<Monhoc> monhocDelete = monHocRepository.findById(MaMonhoc);
        if (monhocDelete.isPresent()) {
            monHocRepository.deleteById(MaMonhoc);
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }
}
