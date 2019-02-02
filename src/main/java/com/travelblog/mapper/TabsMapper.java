package com.travelblog.mapper;

import com.travelblog.dto.TabDTO;
import org.springframework.stereotype.Component;
import com.travelblog.model.Tab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TabsMapper {

    public CompletableFuture<List<TabDTO>> mapTabsIterableToTabDTOS(Iterable<Tab> tabsIterable) {
        List<TabDTO> tabDTOS = new ArrayList<>();
        for(Tab tab : tabsIterable) {
            tabDTOS.add(TabDTO.builder()
                    .id(tab.getId())
                    .name(tab.getName())
                    .build()
            );
        }
        return CompletableFuture.completedFuture(tabDTOS);
    }

}
