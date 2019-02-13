package com.travelblog.mapper;

import com.travelblog.dto.comments.CommentDTO;
import com.travelblog.dto.comments.CommentsListDTO;
import com.travelblog.dto.posts.PostContentDTO;
import com.travelblog.dto.posts.PostContentsListDTO;
import com.travelblog.model.Comment;
import com.travelblog.model.Post;
import com.travelblog.model.Tab;
import com.travelblog.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentsMapper {

    public CommentsListDTO mapToCommentsListDTO(Iterable<Comment> commentsIterable) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for(Comment comment : commentsIterable) {
            CommentDTO commentDTO = mapToCommentDTO(comment);
            commentDTOs.add(commentDTO);
        }
        return CommentsListDTO.builder()
                .comments(commentDTOs)
                .build();
    }

    public CommentDTO mapToCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .name(comment.getName())
                .createdAt(comment.getCreatedAt())
                .content(comment.getContent())
                .email(comment.getEmail())
                .build();
    }

    public Comment mapToComment(CommentDTO commentDTO) {
        return Comment.builder()
                .name(commentDTO.getName())
                .createdAt(new Date())
                .content(commentDTO.getContent())
                .email(commentDTO.getEmail())
                .build();
    }

    public Comment addPostId(Comment comment, Long postId) {
        comment.setPost(Post.builder().id(postId).build());
        return comment;
    }

}
