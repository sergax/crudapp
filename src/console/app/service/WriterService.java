package console.app.service;

import console.app.model.Post;
import console.app.model.Writer;
import java.util.List;

public interface WriterService extends GenericService<Writer, Long> {

    void create(String name, List<Post> postList) throws Exception;

    void update(Long id, String name, List<Post> postList) throws Exception;

    void checkPost(Long id) throws Exception;
}
