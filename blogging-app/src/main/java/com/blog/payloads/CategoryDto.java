package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty(message = "category title must not be empty.")
    @Size(max = 100)
    private String categoryTitle;

    @NotEmpty(message = "category description must not be empty.")
    @Size(max = 250)
    private String categoryDescription;
}
