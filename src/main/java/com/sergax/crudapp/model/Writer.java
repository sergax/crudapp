package com.sergax.crudapp.model;

import lombok.Data;

import java.util.List;

@Data
public class Writer {
    private Long id;
    private String name;
    private List<Post> posts;
}
