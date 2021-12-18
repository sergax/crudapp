package console.app.controller;

import console.app.service.TagService;
import console.app.model.Tag;

import java.util.List;

public class TagController {

    TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    public List<Tag> getAll() throws Exception {
        return tagService.getAll();
    }

    public Tag getById(Long id) throws Exception {
        return tagService.getById(id);
    }

    public void create(String name) throws Exception {
        tagService.create(name);
    }

    public void update(Long id, String name) throws Exception {
        tagService.update(id, name);
    }

    public void delete(Long id) throws Exception {
        tagService.delete(id);
    }
}
