package com.tiennguyen.osahaneat.controller;

import com.tiennguyen.osahaneat.payload.ResponseData;
import com.tiennguyen.osahaneat.service.imp.FIleServiceImp;
import com.tiennguyen.osahaneat.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController()
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuServiceImp menuServiceImp;
    @Autowired
    FIleServiceImp fIleServiceImp;

    @PostMapping()
    public ResponseEntity<?> createMenu(@RequestParam MultipartFile file, @RequestParam String title
            ,@RequestParam String is_freeship, @RequestParam String time_ship
            ,@RequestParam double price, @RequestParam int cate_id){
        ResponseData responseData=new ResponseData();
        boolean isSuccess=menuServiceImp.createMenu(file,title,is_freeship,time_ship,price,cate_id);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?>getFileRestaurant(@PathVariable String filename) {
        Resource resource= fIleServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);


    }
}
