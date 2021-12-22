package com.sergex.crudapp.controller;

import com.sergex.crudapp.service.TagService;
import com.sergex.crudapp.model.Tag;
import com.sergex.crudapp.service.impl.TagServiceImpl;

import java.util.List;

public class TagController {

    private final TagService tagService = new TagServiceImpl();

    public List<Tag> getAll() {
        return tagService.getAll();
    }

    public Tag getById(Long id) {
        return tagService.getById(id);
    }

    public void create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tagService.create(tag);
    }

    public void update(Long id, String name)  {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tagService.update(tag);
    }

    public void delete(Long id) {
        tagService.delete(id);
    }
}
