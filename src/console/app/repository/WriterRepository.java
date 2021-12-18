package console.app.repository;

import console.app.model.Post;
import console.app.model.Writer;

public interface WriterRepository extends GenericRepository<Writer, Long> {

    boolean isContainsPost(Post post) throws Exception;
    void checkPost(Long id) throws Exception;
}
