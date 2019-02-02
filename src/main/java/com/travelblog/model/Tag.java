package com.travelblog.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Getter
    @Setter
    private String name;

}
