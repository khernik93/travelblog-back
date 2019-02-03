package com.travelblog.service;

import com.travelblog.dto.PostContentDTO;
import com.travelblog.model.Post;
import com.travelblog.repository.PostsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

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

    public void createPost(Post post) {
        postsRepository.save(post);
    }

}
