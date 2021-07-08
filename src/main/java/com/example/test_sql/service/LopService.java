package com.example.test_sql.service;

import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.mapper.LopMapper;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.model.Lop;
import com.example.test_sql.repository.KhoaRepository;
import com.example.test_sql.repository.LopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LopService {
    @Autowired
    LopRepository lopRepository;

    @Autowired
    KhoaRepository khoaRepository;

    @Autowired
    LopMapper lopMapper;

    public List<LopDTO> getList(){

        List<Lop> lopList = lopRepository.findAll();
        List<LopDTO> lopDTOList =new ArrayList<LopDTO>();
        for (Lop lop: lopList)
        {
            LopDTO lopDTO = lopMapper.toDTO(lop);
            lopDTOList.add(lopDTO);
        }

        return lopDTOList;
    }

    public LopDTO get(String malop){
        Optional<Lop> lopOptional = lopRepository.findById(malop);
        LopDTO lopDTO = lopMapper.toDTO(lopOptional.get());
        if(lopOptional.isPresent()){
            return lopDTO;
        }
        else {
            throw  new RuntimeException("Không tồn tại bản ghi " + malop);
        }
    }

    public Lop post(LopDTO lopDTO){
        Optional<Khoa> khoaLop = khoaRepository.findById(lopDTO.getMaKhoa());
        Lop lop = lopMapper.toLop(lopDTO);
        lop.setMaKhoa(khoaLop.get());
        lopRepository.save(lop);
        return lop;
    }
    public Lop put(LopDTO lopDTO,String malop){
        Optional<Lop> optionalLop = lopRepository.findById(malop);
        Optional<Khoa> khoaLop = khoaRepository.findById(lopDTO.getMaKhoa());
        Lop lop = lopMapper.toLop(lopDTO);
        lop.setMaKhoa(khoaLop.get());
        optionalLop.get().setMaLop(lop.getMaLop());
        optionalLop.get().setTenLop(lop.getTenLop());
        optionalLop.get().setMaKhoa(lop.getMaKhoa());
        lopRepository.save(optionalLop.get());
        return optionalLop.get();
    }

    public void delete(String malop){
        Optional<Lop> deletelop = lopRepository.findById(malop);
        if(deletelop.isPresent()){
            lopRepository.delete(deletelop.get());

        }
        else {
            throw  new RuntimeException("Không tồn tại bản ghi " + malop);
        }

    }


}
