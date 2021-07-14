package com.example.test_sql.controller;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("")
    public @ResponseBody List<SinhVienDTO> list(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<SinhVienDTO> sinhVienDTOList = sinhVienService.getList(pageable);
        return sinhVienDTOList;
    }
    @GetMapping("/index")
    public  String view(Model model,@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        List<SinhVienDTO> sinhVienDTOList = sinhVienService.getList(pageable);
        model.addAttribute("sv",sinhVienService.getList(pageable));
        return "admin/sinhvien/index.html";
    }
    @GetMapping("/get")
    public @ResponseBody List<Object> listt(){
         List<Object> sinhVienDTOList = sinhVienService.getLists();
        return sinhVienDTOList;
    }


    @GetMapping("/{masinhvien}")
    public @ResponseBody SinhVienDTO get(@PathVariable String masinhvien){
        SinhVienDTO sinhVienDTO = sinhVienService.get(masinhvien);
        return sinhVienDTO;
    }

    @GetMapping("/showForm")
    public String showNewEmployeeForm(Model model) {
        SinhVienDTO sinhVienDTO = new SinhVienDTO();
        model.addAttribute("sinhVienDTO",sinhVienDTO);
        return "admin/sinhvien/add.html";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute("sinhVienDTO") SinhVienDTO sinhVienDTO){
        sinhVienService.post(sinhVienDTO);
        return "redirect:/sinhvien/index";
    }

    @PutMapping("/")
    public @ResponseBody SinhVienDTO put(@RequestBody SinhVienDTO sinhVienDTO){
        sinhVienService.put(sinhVienDTO);
        return sinhVienDTO;
    }

    @DeleteMapping({"/{masinhvien}"})
    public String delete(@PathVariable String masinhvien){
        sinhVienService.delete(masinhvien);
        return "Xóa thành công";
    }

}
