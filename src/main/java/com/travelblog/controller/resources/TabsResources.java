package com.travelblog.controller.resources;

import com.travelblog.dto.tabs.TabsListDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/tab", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TabsResources {

    /**
     * Returns list of tabs
     * @return tabs
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't fetch tabs")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<TabsListDTO> getTabs();

}
