package com.tiennguyen.osahaneat.service;

import com.tiennguyen.osahaneat.dto.UserDTO;
import com.tiennguyen.osahaneat.entity.Users;
import com.tiennguyen.osahaneat.repository.UserRepository;
import com.tiennguyen.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUser() {
        List<Users> listUser= userRepository.findAll();
        List<UserDTO>userDTOS=new ArrayList<>();
        for(Users users:listUser){
            UserDTO userDTO=new UserDTO();

            userDTO.setId(users.getId());
            userDTO.setUserName(users.getUserName());
            userDTO.setPassword(users.getPassword());
            userDTO.setFullname(users.getFullname());

            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
