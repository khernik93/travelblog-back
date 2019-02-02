package com.travelblog.repository;

import com.travelblog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostsRepository extends CrudRepository<Post, Long> {

    Long countByTabId(Long tabId);

    List<Post> findAllByOrderByCreatedAt(Pageable pageable);

    List<Post> findByTabIdOrderByCreatedAt(Long tabId, Pageable pageable);

}
