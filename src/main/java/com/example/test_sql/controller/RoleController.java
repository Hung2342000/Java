package com.example.test_sql.controller;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("")
    public @ResponseBody List<RoleDTO> list(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<RoleDTO> roleDTOList = roleService.getList(pageable);
        return roleDTOList;
    }
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public @ResponseBody RoleDTO postU(@RequestBody RoleDTO roleDTO){
        roleService.post(roleDTO);
        return roleDTO;
    }
    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable(value = "id") long id){
        roleService.delete(id);
        return "oke";
    }

}
