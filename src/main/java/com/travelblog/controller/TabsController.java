package com.travelblog.controller;

import com.travelblog.controller.resources.TabsControllerResources;
import com.travelblog.dto.tabs.TabsListDTO;
import com.travelblog.error.TabsError;
import com.travelblog.exception.TabsException;
import com.travelblog.mapper.TabsMapper;
import com.travelblog.repository.TabsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import com.travelblog.model.Tab;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class TabsController implements TabsControllerResources {

    @Autowired
    private TabsRepository tabsRepository;

    @Autowired
    private TabsMapper tabsMapper;

    @Override
    public CompletableFuture<TabsListDTO> getTabs() {
        try {
            Iterable<Tab> tabsIterable = tabsRepository.findAll();
            return tabsMapper.mapToTabsListDTO(tabsIterable);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new TabsException(new TabsError("Couldn't fetch tabs"));
        }
    }

}
