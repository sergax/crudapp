package console.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;

import console.app.model.Tag;
import console.app.repository.PostRepository;
import console.app.repository.TagRepository;
import console.app.service.TagService;
import java.util.List;

@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private TagRepository tagRepo;
    private PostRepository postRepo;

    @Override
    public Tag getById(Long id) throws Exception {
        return tagRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Tag tag = new Tag();
        if(postRepo.isContainsTag(tag)) {
            throw  new Exception("Can't delete tag");
        }
        tagRepo.delete(tag);
    }

    @Override
    public List<Tag> getAll() throws Exception {
        return tagRepo.getAll();
    }

    @Override
    public void create(String name) throws Exception {
        Tag tag = new Tag();
        tag.setTagId(tagRepo.getLastId() + 1);
        tag.setTagName(name);
        tagRepo.save(tag);
    }

    @Override
    public void update(Long id, String name) throws Exception {
        Tag tag = new Tag();
        tag.setTagId(id);
        tag.setTagName(name);
        tagRepo.update(tag);
    }
}
