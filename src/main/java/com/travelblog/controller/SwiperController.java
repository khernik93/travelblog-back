package com.travelblog.controller;

import com.travelblog.mapper.SwiperMapper;
import com.travelblog.mapper.TabsMapper;
import com.travelblog.service.SwiperService;
import org.springframework.web.bind.annotation.*;

import com.travelblog.model.Tab;
import com.travelblog.service.TabsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    @Autowired
    private SwiperMapper swiperMapper;

    @GetMapping("/swiperphoto")
    public CompletableFuture<Map<String, List<String>>> getSwiperPhotos() {
        return swiperMapper.mapSwiperPhotosIterableToSwiperPhotosMap(swiperService.getAll());
    }

}
