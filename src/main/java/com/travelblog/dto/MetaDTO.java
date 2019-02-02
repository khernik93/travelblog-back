package com.travelblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MetaDTO {
    private Integer start;
    private Integer end;
    private Long total;
}
