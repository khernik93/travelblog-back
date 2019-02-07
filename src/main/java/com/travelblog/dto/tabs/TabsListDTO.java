package com.travelblog.dto.tabs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TabsListDTO {
    private List<TabDTO> tabs;
}
