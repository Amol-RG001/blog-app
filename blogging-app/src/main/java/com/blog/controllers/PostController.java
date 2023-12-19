package com.blog.controllers;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    //create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createNewPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto newCreatedPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(newCreatedPost,HttpStatus.CREATED);
    }

    //get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getListOfPostByUser(
            @PathVariable Integer userId){
        List<PostDto> posts1 = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts1,HttpStatus.OK);
    }

    //get posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getListOfPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts2 = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts2,HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/post/{postId}")
    public  ResponseEntity<PostDto>getPostsById(@PathVariable Integer postId){
        PostDto pId = this.postService.getPostById(postId);
        return  new ResponseEntity<PostDto>(pId,HttpStatus.OK);
    }

    //get post all
    @GetMapping("/posts")
    public  ResponseEntity<PostResponse>getPosts(
            @RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue ="4",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId", required = false) String sortBy
            )
    {
        PostResponse postRes = this.postService.getAllPosts(pageNumber,pageSize,sortBy);
        return new ResponseEntity<PostResponse>(postRes,HttpStatus.OK);
    }

    //update post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is sucessfully deleted.",true);
    }

}
