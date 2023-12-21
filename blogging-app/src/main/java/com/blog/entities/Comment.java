package com.blog.entities;

import com.blog.payloads.PostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
