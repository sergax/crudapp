package com.sergax.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sergax.crudapp.model.Writer;
import lombok.AllArgsConstructor;
import com.sergax.crudapp.repository.WriterRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class GsonWriterRepositoryImpl implements WriterRepository {

    private final static String FILE_NAME = "/home/sergik/Documents/JAVA/IdeaProjects/crudapp/src/main/resources/files/writers.json";

    @Override
    public Writer getById(Long id) {
        List<Writer> writers = getAllWritersInternal();
        return writers.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        List<Writer> writers = getAllWritersInternal();
        writers.removeIf(t -> t.getId().equals(id));
        writeWritersToFile(writers);
    }

    @Override
    public Writer update(Writer item) {
        List<Writer> writers = getAllWritersInternal();
        writers.forEach(t -> {
            if (t.getId().equals(item.getId())) {
                t.setId(item.getId());
            }
        });
        writeWritersToFile(writers);
        return item;
    }

    @Override
    public Writer save(Writer item) {
        List<Writer> writers = getAllWritersInternal();
        Long newMaxId = generateNewId(writers);
        item.setId(newMaxId);
        writers.add(item);
        writeWritersToFile(writers);
        return item;
    }

    @Override
    public List<Writer> getAll() {
        return getAllWritersInternal();
    }

    private Long generateNewId(List<Writer> writers) {
        Writer writerWithMaxId = writers.stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return Objects.nonNull(writerWithMaxId) ? writerWithMaxId.getId() + 1 : 1L;
    }

    private List<Writer> getAllWritersInternal() {
        //READ ALL TAGS FROM FILE USING GSON ADN RETURN LIST
        List<Writer> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type type = new TypeToken<List<Writer>>() {
            }.getType();
            list = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void writeWritersToFile(List<Writer> items) {
        // WRITE ALL ITEMS TO FILE
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
