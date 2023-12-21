package com.blog.entities;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title", length = 105, nullable = false)
    private  String postTitle;

    @Column(length = 10000)
    private String content;
    private  String imageName;
    private Date contentPostDate;

    @ManyToOne
    @JoinColumn(name = "category_id") // TO CUSTOMIZE NAME IN DB
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> userComments = new HashSet<>();

}
