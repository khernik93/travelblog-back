package com.travelblog.controller;

import com.travelblog.controller.resources.CommentsControllerResources;
import com.travelblog.dto.comments.CommentDTO;
import com.travelblog.dto.comments.CommentsListDTO;
import com.travelblog.error.CommentsError;
import com.travelblog.exception.CommentsException;
import com.travelblog.mapper.CommentsMapper;
import com.travelblog.model.Comment;
import com.travelblog.model.Post;
import com.travelblog.repository.CommentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class CommentsController implements CommentsControllerResources {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsMapper commentsMapper;

    public CompletableFuture<CommentsListDTO> getComments(Long postId) {
        Iterable<Comment> comments;
        try {
            comments = commentsRepository.findAllByPostId(postId);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new CommentsException(new CommentsError("Couldn't fetch comments"));
        }

        CommentsListDTO commentsListDTO = commentsMapper.mapToCommentsListDTO(comments);

        return CompletableFuture.completedFuture(commentsListDTO);
    }

    public CompletableFuture<CommentDTO> addComment(CommentDTO commentDTO, Long postId) {
        Comment comment = commentsMapper.mapToComment(commentDTO);
        comment.setPost(Post.builder().id(postId).build());
        Comment newComment;
        try {
            newComment = commentsRepository.save(comment);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new CommentsException(new CommentsError("Couldn't add a comment"));
        }
        CommentDTO newCommentDTO = commentsMapper.mapToCommentDTO(newComment);
        return CompletableFuture.completedFuture(newCommentDTO);
    }

}
