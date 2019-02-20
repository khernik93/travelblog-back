package com.travelblog.service.impl;

import com.travelblog.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${static-files-path}")
    private String staticFilesPath;

    public String store(MultipartFile multipartFile) {
        File file;
        try {
            ClassPathResource res = new ClassPathResource(staticFilesPath);
            file = new File(res.getPath() + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file.getAbsoluteFile());
            return multipartFile.getOriginalFilename();
        } catch (IOException e) {

        }
        return null;
    }

}
