package com.example.test_sql.controller;

import com.example.test_sql.dto.BaiThiDTO;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.BaiThiRepository;
import com.example.test_sql.service.BaiThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baithi")
public class BaiThiController {
    @Autowired
    BaiThiService baiThiService = new BaiThiService();

    @GetMapping("")
    public List<BaiThiDTO> list(){
        List<BaiThiDTO> baiThiDTOList = baiThiService.list();
        return baiThiDTOList;
    }

    @GetMapping("/{mabaithi}")
    public BaiThiDTO get(@PathVariable String mabaithi){
        BaiThiDTO baiThiDTO = baiThiService.get(mabaithi);
        return baiThiDTO;
    }

    @PostMapping("")
    public String post(@RequestBody BaiThiDTO baiThiDTO){

        baiThiService.post(baiThiDTO);
        return "Thêm thành công";
    }

    @PutMapping("/{mabaithi}")
    public String put(@RequestBody BaiThiDTO baiThiDTO,@PathVariable String mabaithi){
        baiThiService.put(baiThiDTO,mabaithi);
        return "Sửa thành công";

    }
    @DeleteMapping("/{mabaithi}")
    public String delete(@PathVariable("mabaithi") String mabaithi){
        baiThiService.delete(mabaithi);
        return "Xóa thành công";

    }

}
