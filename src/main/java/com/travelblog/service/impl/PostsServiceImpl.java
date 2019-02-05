package com.travelblog.service.impl;

import com.travelblog.model.Post;
import com.travelblog.model.Tag;
import com.travelblog.repository.PostsRepository;

import com.travelblog.service.PostsService;
import com.travelblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TagsService tagsService;

    public Integer adjustEndParameter(Integer end, Long total) {
        Integer totalInt = total.intValue();
        return (end == null || end > totalInt - 1) ? totalInt - 1 : end;
    }

    public Iterable<Post> getChunk(Integer start, Integer end) {
        Pageable chunk = this.covnertOffsetToPage(start, end);
        return postsRepository.findAllByOrderByCreatedAt(chunk);
    }

    public Iterable<Post> getChunkByTabId(Long tabId, Integer start, Integer end) {
        Pageable chunk = this.covnertOffsetToPage(start, end);
        return postsRepository.findByTabIdOrderByCreatedAt(tabId, chunk);
    }

    private Pageable covnertOffsetToPage(Integer start, Integer end) {
        Integer size = end - start + 1;
        Integer page = (size != 0) ? start/size : 0;
        return PageRequest.of(page, size);
    }

    @Transactional
    public Post createPost(Post post) {
        Post newPost = postsRepository.save(post);
        for (Tag tag : post.getTags()) {
            tag.setPost(newPost);
            tagsService.createTag(tag);
        }
        return newPost;
    }

}
