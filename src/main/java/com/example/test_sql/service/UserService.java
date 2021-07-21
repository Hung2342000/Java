package com.example.test_sql.service;
import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.mapper.UserMapper;
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
public class UserService  {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public RoleRepository roleRepository;

    public List<UserDTO> getList(Pageable pageable){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:users) {
            UserDTO userDTO = userMapper.toUserDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    public UserDTO post(UserDTO userDTO){

            List<String> stringList = userDTO.getRoles();
            User userpost = userMapper.toUser(userDTO);
            List<Role> roleList = new ArrayList<>();
            for (int i=0;i<stringList.size();i++){
                Role role = roleRepository.findByName(stringList.get(i));
                roleList.add(role);
            }
            userpost.setRoles(roleList);
            userRepository.save(userpost);
            return userDTO;
    }
    public String delete(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return "oke";

        }
        else {
            throw new RuntimeException("không có bản ghi");
        }

    }

}
