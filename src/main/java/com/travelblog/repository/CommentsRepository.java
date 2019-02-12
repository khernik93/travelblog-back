package com.travelblog.repository;

import com.travelblog.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Long> {

    Iterable<Comment> findAllByPostId(Long postId);

}
