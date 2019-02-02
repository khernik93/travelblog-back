package com.travelblog.mapper;

import com.travelblog.model.SwiperPhoto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class SwiperMapper {

    public CompletableFuture<Map<String, List<String>>> mapSwiperPhotosIterableToSwiperPhotosMap
            (Iterable<SwiperPhoto> swiperPhotosIterable) {
        Map<String, List<String>> swiperPhotosMap = new HashMap<>();
        for(SwiperPhoto swiperPhoto : swiperPhotosIterable) {
            List<String> urls = swiperPhotosMap.get(swiperPhoto.getTab().getName());
            if (urls == null) {
                swiperPhotosMap.put(swiperPhoto.getTab().getName(), new ArrayList<>());
            }
            swiperPhotosMap.get(swiperPhoto.getTab().getName()).add(swiperPhoto.getUrl());
        }
        return CompletableFuture.completedFuture(swiperPhotosMap);
    }

}
