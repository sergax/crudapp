package com.sergax.crudapp.service.impl;

import com.sergax.crudapp.repository.gson.GsonTagRepositoryImpl;
import com.sergax.crudapp.service.TagService;
import lombok.AllArgsConstructor;
import com.sergax.crudapp.model.Tag;
import com.sergax.crudapp.repository.TagRepository;

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
