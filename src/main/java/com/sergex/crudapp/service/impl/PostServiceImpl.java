package com.sergex.crudapp.service.impl;

import com.sergex.crudapp.repository.gson.GsonPostRepositoryImpl;
import com.sergex.crudapp.service.PostService;
import lombok.AllArgsConstructor;
import com.sergex.crudapp.model.Post;
import com.sergex.crudapp.repository.PostRepository;

import java.util.List;

@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository = new GsonPostRepositoryImpl();

    @Override
    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.update(post);
    }
}
