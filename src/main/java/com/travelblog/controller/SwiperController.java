package com.travelblog.controller;

import com.travelblog.controller.resources.SwiperResources;
import com.travelblog.dto.swiper.SwiperDTO;
import com.travelblog.error.SwiperError;
import com.travelblog.exception.SwiperException;
import com.travelblog.mapper.SwiperMapper;
import com.travelblog.model.Swiper;
import com.travelblog.repository.SwiperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class SwiperController implements SwiperResources {

    @Autowired
    private SwiperRepository swiperRepository;

    @Autowired
    private SwiperMapper swiperMapper;

    @Override
    public CompletableFuture<SwiperDTO> getPhotos() {
        try {
            Iterable<Swiper> swiperPhotoIterable = swiperRepository.findAll();
            return swiperMapper.mapToSwiperDTO(swiperPhotoIterable);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new SwiperException(new SwiperError("Couldn't fetch swiper photos"));
        }
    }

}
