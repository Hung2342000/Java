package com.example.test_sql.controller;

import com.example.test_sql.model.Monhoc;
import com.example.test_sql.service.KhoaService;
import com.example.test_sql.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monhoc")
public class MonHocControlller {
    @Autowired
    public MonHocService monHocService = new MonHocService();

    @GetMapping("")
    public List<Monhoc> getList(){
        List<Monhoc> monhocList = monHocService.getList();
        return monhocList;
    }

    @GetMapping("/{mamonhoc}")
    public Monhoc get(@PathVariable("mamonhoc") String ma){
        Monhoc monhoc = monHocService.get(ma);
        return monhoc;
    }

    @PostMapping("")
    public String Post(@RequestBody Monhoc monhoc){
        monHocService.post(monhoc);
        return "Thêm thành công";
    }

    @PutMapping("/{mamonhoc}")
    public String put(@RequestBody Monhoc monhoc, @PathVariable("mamonhoc") String ma){
        monHocService.put(monhoc,ma);
        return "Sửa thành công";
    }

    @DeleteMapping("/{mamonhoc}")
    public String delete(@PathVariable("mamonhoc") String ma){
        monHocService.delete(ma);
        return "Xóa thành công";
    }


}
