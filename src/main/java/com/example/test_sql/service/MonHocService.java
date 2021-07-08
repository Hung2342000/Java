package com.example.test_sql.service;

import com.example.test_sql.model.Khoa;
import com.example.test_sql.model.Monhoc;
import com.example.test_sql.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Component
public class MonHocService {
    @Autowired
    MonHocRepository monHocRepository ;

    public List<Monhoc> getList(){
        List<Monhoc> monhocList = monHocRepository.findAll();
        return monhocList;
    }

    public Monhoc get(String MaMonhoc){
        Optional<Monhoc> monhoc = monHocRepository.findById(MaMonhoc);
        if(monhoc.isPresent()){
            return monhoc.get();
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi " + MaMonhoc);
        }
    }

    public void post(Monhoc monhoc){
        Optional<Monhoc> monhocPost = monHocRepository.findById(monhoc.getMaMonHoc());
        if(monhocPost.isPresent()){
            throw new RuntimeException("Đã tồn tại bản ghi " );
        }
        else{ monHocRepository.save(monhoc);}
    }

    public void put(Monhoc monhoc, String MaMonhoc){
        Optional<Monhoc> monhocPut = monHocRepository.findById(MaMonhoc);
        if (monhocPut.isPresent()){
            monhocPut.get().setTenMonHoc(monhoc.getTenMonHoc());
            monHocRepository.save(monhoc);
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
