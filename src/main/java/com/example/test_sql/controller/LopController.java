package com.example.test_sql.controller;

import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.model.Lop;
import com.example.test_sql.service.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/lop")
public class LopController {
    @Autowired
    public LopService lopService ;

    @PreAuthorize("hasAuthority('list_lop')")
    @GetMapping("/list")
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

    @PreAuthorize("hasAuthority('create_lop')")
    @PostMapping("/post")
    public LopDTO post(@RequestBody LopDTO lopDTO){
        lopService.post(lopDTO);
        return lopDTO;
    }

    @PreAuthorize("hasAuthority('update_lop')")
    @PutMapping("/put")
    public LopDTO put(@RequestBody LopDTO lopDTO){
        lopService.put(lopDTO);
        return lopDTO;
    }

    @PreAuthorize("hasAuthority('delete_lop')")
    @DeleteMapping("delete/{malop}")
    public String delete(@PathVariable("malop") String malop){
        lopService.delete(malop);
        return "Xóa thành công";
    }
}
