package com.blog.controllers;
import com.blog.config.AppConstants;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.ImageResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    private String image;
    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

            String fileName = this.fileService.uploadImage(path, image);
            PostDto postDto = this.postService.getPostById(postId);
            postDto.setImageName(fileName);
            PostDto updatedPost = this.postService.updatePost(postDto,postId);
            return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);

    }

    //method to serve files
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse httpRes
    )throws IOException{
        InputStream resource = this.fileService.getResource(path, imageName);
        httpRes.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,httpRes.getOutputStream());
    }

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
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDirection",defaultValue=AppConstants.SORT_DIRECTION, required = false) String sortDirection
    )
    {
        PostResponse postRes = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDirection);
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

    //search title
    @GetMapping("/posts/search/{keywords}")
    ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> matchingResult = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(matchingResult,HttpStatus.OK);
    }
}
