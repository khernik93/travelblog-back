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
            CommentDTO commentDTO = CommentDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .build();
            commentDTOs.add(commentDTO);
        }
        return CommentsListDTO.builder()
                .comments(commentDTOs)
                .build();
    }

}
