package com.example.test_sql.controller;

import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.model.Lop;
import com.example.test_sql.service.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lop")
public class LopController {
    @Autowired
    public LopService lopService = new LopService();

    @GetMapping("")
    public List<LopDTO> getList(){
        List<LopDTO> lopDTOList = new ArrayList<LopDTO>();
        lopDTOList = lopService.getList();
        return lopDTOList;
    }

    @GetMapping("/{malop}")
    public LopDTO get(@PathVariable String malop){
        LopDTO lopDTO = lopService.get(malop);
        return lopDTO;
    }

    @PostMapping("")
    public String post(@RequestBody LopDTO lopDTO){
        lopService.post(lopDTO);
        return "Thêm thành công";
    }

    @PutMapping("/{malop}")
    public String post(@RequestBody LopDTO lopDTO,@PathVariable("malop") String malop){
        lopService.put(lopDTO,malop);
        return "Cập nhật thành công";
    }

    @DeleteMapping("/{malop}")
    public String delete(@PathVariable("malop") String malop){
        lopService.delete(malop);
        return "Xóa thành công";
    }
}
