package console.app.controller;

import console.app.model.Post;
import console.app.service.WriterService;
import console.app.model.Writer;

import java.util.List;

public class WriterController {

    WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    public List<Writer> getAll() throws Exception {
        return writerService.getAll();
    }

    public Writer getById(Long id) throws Exception {
        return writerService.getById(id);
    }

    public void create(String name, List<Post> postList) throws Exception {
        writerService.create(name, postList);
    }

    public void update(Long id, String name, List<Post> postList) throws Exception {
        writerService.update(id, name, postList);
    }

    public void delete(Long id) throws Exception {
        writerService.delete(id);
    }

    public void checkPost(Long id) throws Exception {
        writerService.checkPost(id);
    }
}
