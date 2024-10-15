package com.tiennguyen.osahaneat.controller;

import com.tiennguyen.osahaneat.payload.ResponseData;
import com.tiennguyen.osahaneat.service.imp.FIleServiceImp;
import com.tiennguyen.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FIleServiceImp fIleServiceImp;
    @Autowired
    RestaurantServiceImp restaurantServiceImp;


    @PostMapping("/upload")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,@RequestParam String title
            ,@RequestParam  String subtitle,@RequestParam String description
            ,@RequestParam boolean isFreeship
            ,@RequestParam String address,@RequestParam String openDate){
        ResponseData responseData=new ResponseData();
        boolean isSuccess=restaurantServiceImp.insertRestaurant(file,title,subtitle,description,isFreeship,address,openDate);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getHomeRestaurant(){
        ResponseData responseData=new ResponseData();

        responseData.setData(restaurantServiceImp.getHomePageRestaurant());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?>getFileRestaurant(@PathVariable String filename) {
       Resource resource= fIleServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);


    }
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id){
        ResponseData responseData=new ResponseData();

       responseData.setData(restaurantServiceImp.getDetailRestaurant(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


}
