package com.sergex.crudapp.model;

import com.sergex.crudapp.utils.PostStatus;
import lombok.Data;

import java.util.List;

@Data
public class Post {
    private Long id;
    private String content;
    private List<Tag> tags;
    private PostStatus status;
}
