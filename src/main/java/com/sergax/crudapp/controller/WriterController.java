package com.sergax.crudapp.controller;

import com.sergax.crudapp.model.Post;
import com.sergax.crudapp.model.Writer;
import com.sergax.crudapp.service.WriterService;
import com.sergax.crudapp.service.impl.WriterServiceImpl;

import java.util.List;

public class WriterController {

    private final WriterService writerService = new WriterServiceImpl();

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public Writer getById(Long id) {
        return writerService.getById(id);
    }

    public void create(String name, List<Post> posts) {
        Writer writer = new Writer();
        writer.setName(name);
        writer.setPosts(posts);
        writerService.create(writer);
    }

    public void update(Long id, String name, List<Post> posts) {
        Writer writer = new Writer();
        writer.setId(id);
        writer.setName(name);
        writer.setPosts(posts);
        writerService.update(writer);
    }

    public void delete(Long id) {
        writerService.delete(id);
    }
}
