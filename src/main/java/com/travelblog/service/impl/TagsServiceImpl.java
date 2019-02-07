package com.travelblog.service.impl;

import com.travelblog.model.Tag;
import com.travelblog.repository.TagsRepository;
import com.travelblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    public void createTag(Tag tag) {
        tagsRepository.save(tag);
    }

}
