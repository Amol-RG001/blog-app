package com.blog.payloads;

import com.blog.entities.Category;
import com.blog.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    @NotEmpty(message = "post title should not be empty.")
    @Size(max = 105, message = "max length of title is 105 characters. ")
    private String postTitle;
    @NotEmpty(message = "content should not be empty. ")
    private String content;

    private String imageName = "DEFAULT.PNG";

    private Date contentPostDate;
    private CategoryDto category;
    private UserDto user;

}
