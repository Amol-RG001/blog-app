package com.blog.controllers;

import com.blog.entities.Comment;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> doComment(@RequestBody CommentDto comment,
                                                @PathVariable Integer postId) {
        CommentDto publishedComment = this.commentService.createComment(comment,postId);
        return  new ResponseEntity<CommentDto>(publishedComment, HttpStatus.CREATED);
    }

    @DeleteMapping ("/delete/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
         this.commentService.deleteComment(commentId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfuly!",true), HttpStatus.OK);
    }
}
