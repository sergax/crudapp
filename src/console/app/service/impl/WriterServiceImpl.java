package console.app.service.impl;

import console.app.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import console.app.model.Writer;
import console.app.repository.WriterRepository;
import console.app.service.WriterService;
import java.util.List;

@Data
@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    WriterRepository writerRepo;

    @Override
    public void create(String name, List<Post> postList) throws Exception {
        Writer writer = new Writer();
        writer.setWriterId(writerRepo.getLastId() + 1);
        writer.setWriterName(name);
        writer.setPostList(postList);
        writerRepo.save(writer);
    }

    @Override
    public void update(Long id, String name, List<Post> postList) throws Exception {
        Writer writer = new Writer();
        writer.setWriterId(id);
        writer.setWriterName(name);
        writer.setPostList(postList);
        writerRepo.update(writer);
    }

    @Override
    public void checkPost(Long id) throws Exception {
        writerRepo.checkPost(id);
    }

    @Override
    public Writer getById(Long id) throws Exception {
        return writerRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Writer writer = getById(id);
        writerRepo.delete(writer);
    }

    @Override
    public List<Writer> getAll() throws Exception {
        return writerRepo.getAll();
    }
}
