package com.sergex.crudapp.service.impl;

import com.sergex.crudapp.repository.gson.GsonWriterRepositoryImpl;
import com.sergex.crudapp.service.WriterService;
import lombok.AllArgsConstructor;

import com.sergex.crudapp.model.Writer;
import com.sergex.crudapp.repository.WriterRepository;

import java.util.List;


@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository = new GsonWriterRepositoryImpl();

    @Override
    public Writer getById(Long id) {
        return writerRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        writerRepository.deleteById(id);
    }

    @Override
    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    @Override
    public Writer create(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }
}
