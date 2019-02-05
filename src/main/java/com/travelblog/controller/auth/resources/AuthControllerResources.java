package com.travelblog.controller.auth.resources;

import com.travelblog.dto.auth.CredentialsDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthControllerResources {

    /**
     * Sign in
     * @param credentialsDTO Auth credentials
     * @param request
     * @param response
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Invalid credentials"),
            @ApiResponse(code = 500, message = "Couldn't sign in")
    })
    @PostMapping(path = "/signIn", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<String> signIn(
            @RequestBody CredentialsDTO credentialsDTO,
            HttpServletRequest request,
            HttpServletResponse response);

}
