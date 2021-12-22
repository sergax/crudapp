package com.sergex.crudapp.controller;

import com.sergex.crudapp.model.Post;
import com.sergex.crudapp.model.PostStatus;
import com.sergex.crudapp.model.Tag;
import com.sergex.crudapp.model.Writer;
import com.sergex.crudapp.service.PostService;
import com.sergex.crudapp.service.WriterService;
import com.sergex.crudapp.service.impl.PostServiceImpl;
import com.sergex.crudapp.service.impl.WriterServiceImpl;

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
