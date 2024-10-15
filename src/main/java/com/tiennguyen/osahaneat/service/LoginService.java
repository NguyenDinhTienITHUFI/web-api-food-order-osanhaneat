package com.tiennguyen.osahaneat.service;

import com.tiennguyen.osahaneat.dto.UserDTO;
import com.tiennguyen.osahaneat.entity.Roles;
import com.tiennguyen.osahaneat.entity.Users;
import com.tiennguyen.osahaneat.payload.request.SignUpRequest;
import com.tiennguyen.osahaneat.repository.UserRepository;
import com.tiennguyen.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> getAllUser(){
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

    @Override
    public boolean checkLogin(String username, String password) {
        Users user=userRepository.findByUserName(username);
        return passwordEncoder.matches(password,user.getPassword());

    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Roles roles=new Roles();
        roles.setId(signUpRequest.getRoleId());


        Users users=new Users();
        users.setFullname(signUpRequest.getFullname());
        users.setUserName(signUpRequest.getEmail());
        users.setPassword(signUpRequest.getPassword());
        users.setRoles(roles);
        try {
            userRepository.save(users);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
