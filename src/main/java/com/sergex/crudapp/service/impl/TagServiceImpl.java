package com.sergex.crudapp.service.impl;

import com.sergex.crudapp.repository.gson.GsonTagRepositoryImpl;
import com.sergex.crudapp.service.TagService;
import lombok.AllArgsConstructor;

import com.sergex.crudapp.model.Tag;
import com.sergex.crudapp.repository.PostRepository;
import com.sergex.crudapp.repository.TagRepository;

import java.util.List;

@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository = new GsonTagRepositoryImpl();

    @Override
    public Tag getById(Long id) {
        return tagRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.getAll();
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return tagRepository.update(tag);
    }
}
