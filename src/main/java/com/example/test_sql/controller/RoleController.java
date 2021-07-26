package com.example.test_sql.controller;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    //@PreAuthorize("hasAuthority('list_role')")
    @GetMapping("/list")
    public @ResponseBody List<RoleDTO> list(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<RoleDTO> roleDTOList = roleService.getList(pageable);
        return roleDTOList;
    }

    //@PreAuthorize("hasAuthority('create_role')")
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public @ResponseBody RoleDTO postU(@RequestBody RoleDTO roleDTO){
        roleService.post(roleDTO);
        return roleDTO;
    }

    //@PreAuthorize("hasAuthority('delete_role')")
    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable(value = "id") long id){
        roleService.delete(id);
        return "oke";
    }

}
