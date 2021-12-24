package com.sergax.crudapp.service.impl;

import com.sergax.crudapp.repository.gson.GsonPostRepositoryImpl;
import com.sergax.crudapp.service.PostService;
import lombok.AllArgsConstructor;
import com.sergax.crudapp.model.Post;
import com.sergax.crudapp.repository.PostRepository;

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
