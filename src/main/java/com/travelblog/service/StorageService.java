package com.travelblog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile multipartFile);

}
