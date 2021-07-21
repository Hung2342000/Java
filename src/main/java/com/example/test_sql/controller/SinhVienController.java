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

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/admin/sinhvien")
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
        Integer listpage = (sinhVienService.List().size())/5 + 1;
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        List<SinhVienDTO> sinhVienDTOList = sinhVienService.getList(pageable);
        model.addAttribute("sv",sinhVienService.getList(pageable));
        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);

        return "admin/sinhvien/index.html";
    }
    @GetMapping("/get")
    public @ResponseBody List<Object> listt(){
         List<Object> sinhVienDTOList = sinhVienService.getLists();
        return sinhVienDTOList;
    }


    @GetMapping("index/{masinhvien}")
    public String get(Model model,@RequestParam(value = "search") String masinhvien){
        List<SinhVienDTO> list = new ArrayList<>();
        SinhVienDTO sinhVienDTO = sinhVienService.get(masinhvien);
        list.add(sinhVienDTO);
        model.addAttribute("sv",list);
        model.addAttribute("page",1);
        model.addAttribute("listpage",1);
        model.addAttribute("ma",masinhvien);
        model.addAttribute("sv",sinhVienDTO);
        return "admin/sinhvien/index?page=1";
    }

    @GetMapping("/showForm")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("sinhVienDTO",new SinhVienDTO());
        return "admin/sinhvien/add.html";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute("sinhVienDTO") SinhVienDTO sinhVienDTO){
        sinhVienService.post(sinhVienDTO);
        return "redirect:/sinhvien/index?page=1";
    }

    @GetMapping("/FormUpdate/{masinhvien}")
    public String formUpdate(@PathVariable String masinhvien,Model model ) {
        SinhVienDTO sinhVienDTO = sinhVienService.get(masinhvien);
        model.addAttribute("sinhVienDTO",sinhVienDTO);
        return "admin/sinhvien/update.html";
    }

    @PostMapping("/put")
    public String put(@ModelAttribute("sinhVienDTO") SinhVienDTO sinhVienDTO){
        sinhVienService.put(sinhVienDTO);
        return "redirect:/sinhvien/index?page=1";
    }
    @GetMapping({"/delete/{masinhvien}"})
    public String delete(@PathVariable String masinhvien){
        sinhVienService.delete(masinhvien);
        return "redirect:/sinhvien/index?page=1";
    }
}
