package com.sergex.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sergex.crudapp.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.sergex.crudapp.repository.TagRepository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

@Data
@AllArgsConstructor
public class GsonTagRepositoryImpl implements TagRepository {

    private final static String FILE_NAME = "/home/sergik/Documents/JAVA/IdeaProjects/crudapp/src/main/resources/files/tags.json";

    @Override
    public Tag getById(Long id) {
        List<Tag> tags = getAllTagsInternal();
        return tags.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }


    @Override
    public void deleteById(Long id) {
        List<Tag> tags = getAllTagsInternal();
        tags.removeIf(t -> t.getId().equals(id));
        writeTagsToFile(tags);
    }

    @Override
    public Tag update(Tag item) {
        List<Tag> tags = getAllTagsInternal();
        tags.forEach(t -> {
            if (t.getId().equals(item.getId())) {
                t.setName(item.getName());
            }
        });
        writeTagsToFile(tags);
        return item;
    }

    @Override
    public Tag save(Tag item) {
        List<Tag> tags = getAllTagsInternal();
        Long newMaxId = generateNewId(tags);
        item.setId(newMaxId);
        tags.add(item);
        writeTagsToFile(tags);
        return item;
    }

    @Override
    public List<Tag> getAll() {
        return getAllTagsInternal();
    }

    private Long generateNewId(List<Tag> tags) {
        Tag tagWithMaxId = tags.stream().max(Comparator.comparing(Tag::getId)).orElse(null);
        return Objects.nonNull(tagWithMaxId) ? tagWithMaxId.getId() + 1 : 1L;
    }

    private List<Tag> getAllTagsInternal() {
        //READ ALL TAGS FROM FILE USING GSON AND RETURN LIST
        List<Tag> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type collType = new TypeToken<List<Tag>>() {
            }.getType();
            list = gson.fromJson(reader, collType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void writeTagsToFile(List<Tag> items) {
        // WRITE ALL ITEMS TO FILE
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
