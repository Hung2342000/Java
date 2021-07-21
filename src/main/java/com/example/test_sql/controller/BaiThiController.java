package com.example.test_sql.controller;

import com.example.test_sql.dto.BaiThiDTO;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.BaiThiRepository;
import com.example.test_sql.service.BaiThiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin/baithi")
@Slf4j
public class BaiThiController {
    @Autowired
    private BaiThiService baiThiService ;

    @GetMapping("")
    public List<BaiThiDTO> list(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<BaiThiDTO> baiThiDTOList = baiThiService.list(pageable);
        return baiThiDTOList;
    }

    @GetMapping("/{mabaithi}")
    public BaiThiDTO get(@PathVariable Long mabaithi){
        Logger a = Logger.getLogger("ma bai thi");
        a.info("abc");

        BaiThiDTO baiThiDTO = baiThiService.get(mabaithi);
        return baiThiDTO;
    }

    @GetMapping("/diem/{diem}")
    public Long geaa(@PathVariable Integer diem){
        return baiThiService.a(diem);
    }

    @PostMapping("")
    public BaiThiDTO post(@RequestBody BaiThiDTO baiThiDTO){

        baiThiService.post(baiThiDTO);
        return baiThiService.post(baiThiDTO);
    }

    @PutMapping("")
    public BaiThiDTO put(@RequestBody BaiThiDTO baiThiDTO){
        baiThiService.put(baiThiDTO);
        return baiThiService.put(baiThiDTO);

    }
    @DeleteMapping("/{mabaithi}")
    public String delete(@PathVariable("mabaithi") Long mabaithi){
        baiThiService.delete(mabaithi);
        return "Xóa thành công";

    }

}
