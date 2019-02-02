package com.travelblog.controller;

import com.travelblog.dto.TabDTO;
import com.travelblog.mapper.TabsMapper;
import org.springframework.web.bind.annotation.*;

import com.travelblog.model.Tab;
import com.travelblog.service.TabsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class TabsController {

    @Autowired
    private TabsService tabsService;

    @Autowired
    private TabsMapper tabsMapper;

    @GetMapping("/tab")
    public CompletableFuture<List<TabDTO>> getTabs() {
        return tabsMapper.mapTabsIterableToTabDTOS(tabsService.getAll());
    }

}
