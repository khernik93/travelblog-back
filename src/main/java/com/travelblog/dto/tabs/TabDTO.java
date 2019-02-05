package com.travelblog.dto.tabs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TabDTO {
    private Long id;
    private String name;
}
