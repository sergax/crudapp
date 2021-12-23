package com.sergex.crudapp.controller;

import com.sergex.crudapp.model.Post;
import com.sergex.crudapp.utils.PostStatus;
import com.sergex.crudapp.model.Tag;
import com.sergex.crudapp.service.PostService;
import com.sergex.crudapp.service.impl.PostServiceImpl;

import java.util.List;

public class PostController {

    private final PostService postService = new PostServiceImpl();

    public List<Post> getAll() {
        return postService.getAll();
    }

    public Post getById(Long id) {
        return postService.getById(id);
    }

    public void create(String content, List<Tag> tags, PostStatus postStatus) {
        Post post = new Post();
        post.setContent(content);
        post.setTags(tags);
        post.setStatus(postStatus);
        postService.create(post);
    }

    public void update(Long id, String content, List<Tag> tags)  {
        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.setTags(tags);
        postService.update(post);
    }

    public void delete(Long id) {
        postService.delete(id);
    }
}
