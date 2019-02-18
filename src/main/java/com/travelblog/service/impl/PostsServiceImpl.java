package com.travelblog.service.impl;

import com.travelblog.model.Post;
import com.travelblog.model.Tag;
import com.travelblog.repository.PostsRepository;

import com.travelblog.repository.TagsRepository;
import com.travelblog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TagsRepository tagsRepository;

    public Integer adjustEndParameter(Integer end, Long total) {
        Integer totalInt = total.intValue();
        return (end == null || end > totalInt - 1) ? totalInt - 1 : end;
    }

    public Iterable<Post> getChunk(Integer start, Integer end) {
        Pageable chunk = this.covnertOffsetToPage(start, end);
        return postsRepository.findAllByOrderByCreatedAtDesc(chunk);
    }

    public Iterable<Post> getChunkByTabId(Long tabId, Integer start, Integer end) {
        Pageable chunk = this.covnertOffsetToPage(start, end);
        return postsRepository.findByTabIdOrderByCreatedAtDesc(tabId, chunk);
    }

    private Pageable covnertOffsetToPage(Integer start, Integer end) {
        Integer size = end - start + 1;
        Integer page = (size != 0) ? start/size : 0;
        return PageRequest.of(page, size);
    }

    @Transactional
    public Post updatePost(Post post) {
        Post newPost = postsRepository.save(post);
        List<Tag> newTags = new ArrayList<>();
        tagsRepository.deleteAllByPostId(post.getId());
        for (Tag tag : post.getTags()) {
            tag.setPost(newPost);
            newTags.add(tagsRepository.save(tag));
        }
        newPost.setTags(newTags);
        return newPost;
    }

}
