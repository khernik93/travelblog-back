package com.travelblog.service;

import com.travelblog.model.SwiperPhoto;
import com.travelblog.repository.SwiperPhotosRepository;

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
