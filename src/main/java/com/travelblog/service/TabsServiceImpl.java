package com.travelblog.service;

import com.travelblog.model.Tab;
import com.travelblog.repository.TabsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TabsServiceImpl implements TabsService {

    @Autowired
    private TabsRepository tabsRepository;

    public Iterable<Tab> getAll() {
        return tabsRepository.findAll();
    }

}
