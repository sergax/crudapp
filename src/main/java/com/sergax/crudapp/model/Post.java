package com.sergax.crudapp.model;

import com.sergax.crudapp.utils.PostStatus;
import lombok.Data;

import java.util.List;

@Data
public class Post {
    private Long id;
    private String content;
    private List<Tag> tags;
    private PostStatus status;
}
