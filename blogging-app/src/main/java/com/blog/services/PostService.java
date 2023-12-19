package com.blog.services;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer postId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    // get all posts
    //modify return type as PostResponse
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);

}
