package com.example.test_sql.controller;

import com.example.test_sql.model.User;
import com.example.test_sql.repository.UserRepository;
import com.example.test_sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class loginController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String login(){
        return "admin/login.html";
    }

    @RequestMapping("/user/{name}")
    public @ResponseBody  String a(@PathVariable(value = "name") String name){
        return userRepository.findByEmail(name).getEmail();
    }

    @RequestMapping("/user/abc/{id}")
    public @ResponseBody User a(@PathVariable(value = "id") long id){
        return userRepository.findById(id).get();
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin/admin.html";
    }

}
