package com.travelblog.service.impl;

import com.travelblog.dto.PostContentDTO;
import com.travelblog.model.Post;
import com.travelblog.model.Tag;
import com.travelblog.repository.PostsRepository;

import com.travelblog.repository.TagsRepository;
import com.travelblog.service.PostsService;
import com.travelblog.service.TagsService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TagsService tagsService;

    public Long getCount(Long tabId) {
        return postsRepository.countByTabId(tabId);
    }

    public Iterable<Post> getChunk(Integer start, Integer end) {
        Pageable chunk = this.covnertOffsetToPage(start, end);
        return postsRepository.findAllByOrderByCreatedAt(chunk);
    }

    public Iterable<Post> getChunk(Long tabId, Integer start, Integer end) {
        Pageable chunk = this.covnertOffsetToPage(start, end);
        return postsRepository.findByTabIdOrderByCreatedAt(tabId, chunk);
    }

    private Pageable covnertOffsetToPage(Integer start, Integer end) {
        Integer size = end - start + 1;
        Integer page = (size != 0) ? start/size : 0;
        return PageRequest.of(page, size);
    }

    public Optional<Post> getById(Long id) {
        return postsRepository.findById(id);
    }

    @Transactional
    public void createPost(Post post) {
        Post newPost = postsRepository.save(post);
        for (Tag tag : post.getTags()) {
            tag.setPost(newPost);
            tagsService.createTag(tag);
        }
    }

}
