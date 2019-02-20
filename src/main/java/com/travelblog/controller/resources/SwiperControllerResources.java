package com.travelblog.controller.resources;

import com.travelblog.dto.swiper.SwiperDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/api/swiper", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SwiperControllerResources {

    /**
     * Returns map of swiper photos
     * @return tabs
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't fetch swiper photos")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<SwiperDTO> getPhotos();

}
