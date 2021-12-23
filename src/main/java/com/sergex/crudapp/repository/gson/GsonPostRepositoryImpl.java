package com.sergex.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sergex.crudapp.model.Post;
import lombok.AllArgsConstructor;
import com.sergex.crudapp.repository.PostRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

@AllArgsConstructor
public class GsonPostRepositoryImpl implements PostRepository {

    private final static String FILE_NAME = "/home/sergik/Documents/JAVA/IdeaProjects/crudapp/src/main/resources/files/posts.json";

    @Override
    public Post getById(Long id) {
        List<Post> posts = getAllPostsInternal();
        return posts.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }


    @Override
    public void deleteById(Long id) {
        List<Post> posts = getAllPostsInternal();
        posts.removeIf(t -> t.getId().equals(id));
        writePostsToFile(posts);
    }

    @Override
    public Post update(Post item) {
        List<Post> posts = getAllPostsInternal();
        posts.forEach(t -> {
            if (t.getId().equals(item.getId())) {
                t.setContent(item.getContent());
            }
        });
        writePostsToFile(posts);
        return item;
    }

    @Override
    public Post save(Post item) {
        List<Post> posts = getAllPostsInternal();
        Long newMaxId = generateNewId(posts);
        item.setId(newMaxId);
        posts.add(item);
        writePostsToFile(posts);
        return item;
    }

    @Override
    public List<Post> getAll() {
        return getAllPostsInternal();
    }

    private Long generateNewId(List<Post> posts) {
        Post postWithMaxId = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.nonNull(postWithMaxId) ? postWithMaxId.getId() + 1 : 1L;
    }

    private List<Post> getAllPostsInternal() {
        //READ ALL TAGS FROM FILE USING GSON ADN RETURN LIST
        List<Post> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type collType = new TypeToken<List<Post>>() {
            }.getType();
            list = gson.fromJson(reader, collType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void writePostsToFile(List<Post> items) {
        // WRITE ALL ITEMS TO FILE
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
