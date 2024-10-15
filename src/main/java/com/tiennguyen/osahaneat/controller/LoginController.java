package com.tiennguyen.osahaneat.controller;

import com.tiennguyen.osahaneat.payload.ResponseData;
import com.tiennguyen.osahaneat.payload.request.SignUpRequest;
import com.tiennguyen.osahaneat.service.LoginService;
import com.tiennguyen.osahaneat.service.imp.LoginServiceImp;
import com.tiennguyen.osahaneat.utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?>signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData=new ResponseData();

        if(loginServiceImp.checkLogin(username,password)){
            String token=jwtUtilsHelper.generateToken(username);
            responseData.setData(token);


        }
        else {
            responseData.setData("");
            responseData.setIsSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?>signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData=new ResponseData();
        responseData.setData(loginServiceImp.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
