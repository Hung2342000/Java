package com.example.test_sql.controller;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("")
    public List<SinhVienDTO> list(){
        List<SinhVienDTO> sinhVienDTOList = sinhVienService.getList();
        return sinhVienDTOList;
    }

    @GetMapping("/{masinhvien}")
    public SinhVienDTO get(@PathVariable String masinhvien){
        SinhVienDTO sinhVienDTO = sinhVienService.get(masinhvien);
        return sinhVienDTO;
    }

    @PostMapping("")
    public String post(@RequestBody SinhVienDTO sinhVienDTO){
        sinhVienService.post(sinhVienDTO);
        return "Thêm thành công";
    }

    @PutMapping("/{masinhvien}")
    public String put(@RequestBody SinhVienDTO sinhVienDTO,@PathVariable String masinhvien){
        sinhVienService.put(sinhVienDTO,masinhvien);
        return "Sửa thành công";
    }

    @DeleteMapping({"/{masinhvien}"})
    public String delete(@PathVariable String masinhvien){
        sinhVienService.delete(masinhvien);
        return "Xóa thành công";
    }

}
