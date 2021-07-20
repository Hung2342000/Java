package com.example.test_sql.controller;

import com.example.test_sql.dto.KhoaDTO;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.repository.KhoaRepository;
import com.example.test_sql.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/khoa")
public class KhoaController {
    @Autowired
    KhoaService khoaService ;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<KhoaDTO> getlist(@RequestParam Integer page){
        Pageable pageable = PageRequest.of(page-1,2);
       List<KhoaDTO> khoaDTOList = khoaService.getlist(pageable);
       return khoaDTOList;
    };

    @RequestMapping(value = "",method = RequestMethod.POST)
    public KhoaDTO Post(@RequestBody KhoaDTO khoaDTO){

        khoaService.Post(khoaDTO);
        return khoaDTO;
    };

    @RequestMapping(value = "/{makhoa}",method = RequestMethod.GET)
    public KhoaDTO get(@PathVariable("makhoa") String makhoa) {
        KhoaDTO khoaDTOget = khoaService.get(makhoa);
        return khoaDTOget;
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public KhoaDTO Put(@RequestBody KhoaDTO khoaDTO){
        khoaService.Put(khoaDTO);
        return khoaDTO;
    };

    @RequestMapping(value = "/{makhoa}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("makhoa") String makhoa){
        khoaService.Delete(makhoa);
        return "Xóa thành công";
    };

}
