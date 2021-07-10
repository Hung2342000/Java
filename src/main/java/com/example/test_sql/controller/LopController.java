package com.example.test_sql.controller;

import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.model.Lop;
import com.example.test_sql.service.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lop")
public class LopController {
    @Autowired
    public LopService lopService ;

    @GetMapping("")
    public List<LopDTO> getList(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<LopDTO> lopDTOList = lopService.getList(pageable);
        return lopDTOList;
    }

    @GetMapping("/{malop}")
    public LopDTO get(@PathVariable String malop){
        LopDTO lopDTO = lopService.get(malop);
        return lopDTO;
    }

    @PostMapping("")
    public LopDTO post(@RequestBody LopDTO lopDTO){
        lopService.post(lopDTO);
        return lopDTO;
    }

    @PutMapping("")
    public LopDTO put(@RequestBody LopDTO lopDTO){
        lopService.put(lopDTO);
        return lopDTO;
    }

    @DeleteMapping("/{malop}")
    public String delete(@PathVariable("malop") String malop){
        lopService.delete(malop);
        return "Xóa thành công";
    }
}
