package com.travelblog.mapper;

import com.travelblog.dto.swiper.SwiperDTO;
import com.travelblog.model.Swiper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class SwiperMapper {

    public CompletableFuture<SwiperDTO> mapToSwiperDTO(Iterable<Swiper> swiperIterable) {
        Map<Long, List<String>> tabPhotos = new HashMap<>();

        for(Swiper swiper : swiperIterable) {
            List<String> photos = tabPhotos.get(swiper.getTab().getId());
            if (photos == null) {
                tabPhotos.put(swiper.getTab().getId(), new ArrayList<>());
            }
            tabPhotos.get(swiper.getTab().getId()).add(swiper.getUrl());
        }

        return CompletableFuture.completedFuture(
                SwiperDTO.builder()
                .tabPhotos(tabPhotos)
                .build()
        );
    }

}
