package com.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> contentList;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;

}
