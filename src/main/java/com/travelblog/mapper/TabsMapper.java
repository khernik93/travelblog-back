package com.travelblog.mapper;

import com.travelblog.dto.tabs.TabDTO;
import com.travelblog.dto.tabs.TabsListDTO;
import org.springframework.stereotype.Component;
import com.travelblog.model.Tab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TabsMapper {

    public CompletableFuture<TabsListDTO> mapToTabsListDTO(Iterable<Tab> tabsIterable) {
        List<TabDTO> tabDTOs = new ArrayList<>();
        for(Tab tab : tabsIterable) {
            tabDTOs.add(TabDTO.builder()
                    .id(tab.getId())
                    .name(tab.getName())
                    .build());
        }
        return CompletableFuture.completedFuture(
                TabsListDTO.builder().tabs(tabDTOs).build()
        );
    }

}
