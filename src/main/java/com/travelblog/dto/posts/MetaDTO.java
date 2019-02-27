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
    private Integer start;
    private Integer end;
    private Long total;
}
