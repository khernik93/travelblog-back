package com.travelblog.service.impl;

import com.travelblog.model.SwiperPhoto;
import com.travelblog.repository.SwiperPhotosRepository;

import com.travelblog.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperPhotosRepository swiperPhotosRepository;

    public Iterable<SwiperPhoto> getAll() {
        return swiperPhotosRepository.findAll();
    }

}
