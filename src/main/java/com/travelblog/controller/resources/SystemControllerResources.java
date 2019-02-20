package com.travelblog.controller.resources;

import com.travelblog.dto.posts.PostContentsListDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/api/system", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SystemControllerResources {

    /**
     * Upload a photo
     * @return photo URL
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldnt upload photo")
    })
    @PostMapping(path = "/uploadPhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<String> uploadPhoto(
            @RequestParam(value = "file", required = true) MultipartFile file,
            HttpServletRequest request);

}
