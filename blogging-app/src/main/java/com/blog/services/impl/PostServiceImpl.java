package com.blog.services.impl;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.CategoryService;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        //User
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
        //Category By id not found throw exception
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default2.png");
        post.setContentPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post createdPost = this.postRepo.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());


       Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post postFound = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        this.postRepo.delete(postFound);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {

        Sort sorty = null;
        if (sortDirection.equalsIgnoreCase("ascend")){
            sorty = Sort.by(sortBy).ascending();
        }
        else{
            sorty = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber,pageSize,sorty);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> x = pagePost.getContent();

        //List<Post> allPosts = this.postRepo.findAll();
        List<PostDto> postDtoList = x.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContentList(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());

        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
      Post post =   this.postRepo.findById(postId)
              .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));

        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtoList = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtoList = posts.stream().map(post-> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtoList;

    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
