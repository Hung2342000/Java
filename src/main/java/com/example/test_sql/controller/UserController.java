package com.example.test_sql.controller;

import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public @ResponseBody List<UserDTO> list(@RequestParam Integer page){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,2);
        List<UserDTO> userDTOList = userService.getList(pageable);
        return userDTOList;

    }
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public UserDTO postU(@RequestBody UserDTO userDTO){
        userService.post(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") long id){
        userService.delete(id);
        return "oke";
    }

}
