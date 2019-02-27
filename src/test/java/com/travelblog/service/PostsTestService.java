package com.travelblog.service;

import com.travelblog.model.Post;
import com.travelblog.model.Tag;
import com.travelblog.repository.PostsRepository;
import com.travelblog.repository.TagsRepository;
import com.travelblog.service.impl.PostsServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostsTestService {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @InjectMocks
    private PostsServiceImpl sut;

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private TagsRepository tagsRepository;

    @Test
    public void TestAdjustEndParameterToLowerTotal() {
        assertResultOfAdjustEndParameter(3, 2L, 1);
    }

    @Test
    public void TestAdjustEndParameterIfNoneWasProvided() {
        assertResultOfAdjustEndParameter(null, 2L, 1);
    }

    private void assertResultOfAdjustEndParameter(Integer end, Long total, Integer expected) {
        Integer actual = sut.adjustEndParameter(end, total);
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void TestGetChunk() {
        Integer start = 0, end = 2;
        sut.getChunk(start, end);
        Mockito.verify(postsRepository, Mockito.times(1))
                .findAllByOrderByCreatedAtDesc(PageRequest.of(0, 3));
    }

    @Test
    public void TestGetChunkByTabId() {
        Long tabId = 5L;
        Integer start = 0, end = 2;
        sut.getChunkByTabId(tabId, start, end);
        Mockito.verify(postsRepository, Mockito.times(1))
                .findByTabIdOrderByCreatedAtDesc(tabId, PageRequest.of(0, 3));
    }

    @Test
    public void testCreatePost() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag());
        tags.add(new Tag());
        Post post = new Post();
        post.setTags(tags);

        when(postsRepository.save(post)).thenReturn(post);
        sut.updatePost(post);
        Mockito.verify(postsRepository, Mockito.times(1)).save(post);
        Mockito.verify(tagsRepository, Mockito.atLeastOnce()).save(tags.get(0));
        Mockito.verify(tagsRepository, Mockito.atLeastOnce()).save(tags.get(1));
    }

}
