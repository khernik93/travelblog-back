package com.travelblog.service;

import com.travelblog.model.Tag;
import com.travelblog.repository.TagsRepository;
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
