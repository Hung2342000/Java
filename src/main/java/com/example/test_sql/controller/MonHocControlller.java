package com.example.test_sql.controller;

import com.example.test_sql.dto.MonHocDTO;
import com.example.test_sql.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monhoc")
public class MonHocControlller {
    @Autowired
    public MonHocService monHocService = new MonHocService();

    @GetMapping("")
    public List<MonHocDTO> getList(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<MonHocDTO> monhocList = monHocService.getList(pageable);
        return monhocList;
    }

    @GetMapping("/{mamonhoc}")
    public MonHocDTO get(@PathVariable("mamonhoc") String ma){
        MonHocDTO monhoc = monHocService.get(ma);
        return monhoc;
    }

    @PostMapping("")
    public MonHocDTO Post(@RequestBody MonHocDTO monhoc){
        monHocService.post(monhoc);
        return monhoc;
    }

    @PutMapping("")
    public MonHocDTO put(@RequestBody MonHocDTO monhoc){
        monHocService.put(monhoc);
        return monHocService.put(monhoc);
    }

    @DeleteMapping("/{mamonhoc}")
    public String delete(@PathVariable("mamonhoc") String ma){
        monHocService.delete(ma);
        return "Xóa thành công";
    }
}
