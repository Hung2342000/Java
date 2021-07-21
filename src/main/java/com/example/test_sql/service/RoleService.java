package com.example.test_sql.service;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.mapper.RoleMapper;
import com.example.test_sql.model.Role;
import com.example.test_sql.model.User;
import com.example.test_sql.repository.RoleRepository;
import com.example.test_sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public List<RoleDTO> getList(Pageable pageable){
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role role:roles) {
            RoleDTO roleDTO = roleMapper.toRoleDTO(role);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    public RoleDTO post(RoleDTO roleDTO){
        List<String> stringList = roleDTO.getUsers();
        Role rolepost = roleMapper.toRole(roleDTO);
        List<User> userList = new ArrayList<>();
        for(int i=0;i<stringList.size();i++){
            User user = userRepository.findByEmail(stringList.get(i));
            userList.add(user);
        }
        rolepost.setUsers(userList);
        roleRepository.save(rolepost);
        return roleDTO;
    }

    public String delete(long id){
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            roleRepository.deleteById(id);
            return "okee";
        }
        else {
            throw new RuntimeException("không có bản ghi");
        }
    }


}
