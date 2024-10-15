package com.tiennguyen.osahaneat.service.imp;

import com.tiennguyen.osahaneat.dto.UserDTO;
import com.tiennguyen.osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username,String password);
    boolean addUser(SignUpRequest signUpRequest);

}
