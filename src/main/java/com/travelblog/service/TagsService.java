package com.travelblog.service;

import com.travelblog.model.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagsService {

    public void createTag(Tag tag);

}
