package com.example.test_sql.controller;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("")
    public List<SinhVienDTO> list(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<SinhVienDTO> sinhVienDTOList = sinhVienService.getList(pageable);
        return sinhVienDTOList;
    }

    @GetMapping("/{masinhvien}")
    public SinhVienDTO get(@PathVariable String masinhvien){
        SinhVienDTO sinhVienDTO = sinhVienService.get(masinhvien);
        return sinhVienDTO;
    }

    @PostMapping("")
    public SinhVienDTO post(@RequestBody SinhVienDTO sinhVienDTO){
        sinhVienService.post(sinhVienDTO);
        return sinhVienDTO;
    }

    @PutMapping("/")
    public SinhVienDTO put(@RequestBody SinhVienDTO sinhVienDTO){
        sinhVienService.put(sinhVienDTO);
        return sinhVienDTO;
    }

    @DeleteMapping({"/{masinhvien}"})
    public String delete(@PathVariable String masinhvien){
        sinhVienService.delete(masinhvien);
        return "Xóa thành công";
    }

}
