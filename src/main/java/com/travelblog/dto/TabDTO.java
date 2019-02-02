package com.travelblog.dto;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TabDTO {

    private Long id;
    private String name;

}
