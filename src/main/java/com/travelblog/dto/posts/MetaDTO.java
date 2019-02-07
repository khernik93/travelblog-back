package com.travelblog.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDTO {
    private Integer start = 0;
    private Integer end = 0;
    private Long total = 0L;
}
