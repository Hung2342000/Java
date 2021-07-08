package com.example.test_sql.controller;

import com.example.test_sql.dto.KhoaDTO;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    KhoaService khoaService = new KhoaService();

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<KhoaDTO> getlist(){
       List<KhoaDTO> khoaDTOList = khoaService.getlist();
       return khoaDTOList;
    };

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String Post(@RequestBody KhoaDTO khoaDTO){

        khoaService.Post(khoaDTO);
        return "Thêm thành công";
    };


    @RequestMapping(value = "/{makhoa}",method = RequestMethod.GET)
    public KhoaDTO get(@PathVariable("makhoa") String makhoa) {
        KhoaDTO khoaDTOget = khoaService.get(makhoa);
        return khoaDTOget;
    }

    @RequestMapping(value = "/{makhoa}",method = RequestMethod.PUT)
    public String Put(@RequestBody KhoaDTO khoaDTO,@PathVariable("makhoa") String makhoa ){
        khoaService.Put(khoaDTO,makhoa);
        return "Sửa thành công";
    };

    @RequestMapping(value = "/{makhoa}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("makhoa") String makhoa){
        khoaService.Delete(makhoa);
        return "Xóa thành công";
    };

}
