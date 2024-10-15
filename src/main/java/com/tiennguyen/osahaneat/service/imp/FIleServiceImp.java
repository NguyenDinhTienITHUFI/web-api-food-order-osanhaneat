package com.tiennguyen.osahaneat.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FIleServiceImp {
     boolean saveFile(MultipartFile file);
     Resource loadFile(String fileName);
}
