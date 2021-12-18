package console.app.repository;

import console.app.model.Post;
import console.app.model.Tag;

public interface PostRepository extends GenericRepository<Post, Long> {
    void checkTag(Long id) throws Exception;
    void checkEditPost(Long id) throws Exception;
    boolean isContainsTag(Tag tag) throws Exception;
}
