package com.example.test_sql.controller;

import com.example.test_sql.dto.MonHocDTO;
import com.example.test_sql.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/monhoc")
public class MonHocControlller {
    @Autowired
    public MonHocService monHocService = new MonHocService();

    @PreAuthorize("hasAuthority('list_monhoc')")
    @GetMapping("/list")
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

    @PreAuthorize("hasAuthority('create_monhoc')")
    @PostMapping("/post")
    public MonHocDTO Post(@RequestBody MonHocDTO monhoc){
        monHocService.post(monhoc);
        return monhoc;
    }

    @PreAuthorize("hasAuthority('update_monhoc')")
    @PutMapping("/put")
    public MonHocDTO put(@RequestBody MonHocDTO monhoc){
        monHocService.put(monhoc);
        return monHocService.put(monhoc);
    }

    @PreAuthorize("hasAuthority('delete_monhoc')")
    @DeleteMapping("/delete/{mamonhoc}")
    public Integer delete(@PathVariable("mamonhoc") String ma){
        monHocService.delete(ma);
        return monHocService.delete(ma);
    }
}
