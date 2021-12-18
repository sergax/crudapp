package console.app.service;

import console.app.model.Post;
import console.app.model.PostStatus;
import console.app.model.Tag;
import java.util.List;

public interface PostService extends GenericService<Post, Long> {
//id,content,list,status

    void create(String content, List<Tag> tagList, PostStatus postStatus) throws Exception;

    void update(Long id, String content, List<Tag> tagList) throws Exception;

    void checkTag(Long id) throws Exception;

    void checkEditPost(Long id) throws Exception;
}
