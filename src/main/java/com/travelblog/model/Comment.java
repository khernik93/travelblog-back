package com.travelblog.model;

import lombok.*;

import javax.persistence.*;

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
    @Column(columnDefinition = "text")
    private String content;

}
