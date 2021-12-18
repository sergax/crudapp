package console.app.controller;

import console.app.model.Post;
import console.app.model.Tag;
import console.app.service.PostService;
import console.app.model.PostStatus;

import java.util.List;

public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public List<Post> getAll() throws Exception {
        return postService.getAll();
    }

    public Post getById(Long id) throws Exception {
        return postService.getById(id);
    }

    public void create(String content, List<Tag> tagList, PostStatus postStatus) throws Exception {
        postService.create(content, tagList, postStatus);
    }

    public void update(Long id, String content, List<Tag> tagList) throws Exception {
        postService.update(id, content, tagList);
    }

    public void delete(Long id) throws Exception {
        postService.delete(id);
    }

    public  void checkTags(Long id) throws  Exception {
        postService.checkTag(id);
    }

    public void checkEditPost(Long id)throws Exception {
        postService.checkEditPost(id);
    }
}
