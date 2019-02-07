package com.travelblog.dto.swiper;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class SwiperDTO {
    private Map<Long, List<String>> tabPhotos;
}
