package com.travelblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Column(columnDefinition = "text")
    private String content;

    @Getter
    @Setter
    @Column(columnDefinition = "created_at")
    private Date createdAt;

}
