package console.app.service.impl;

import console.app.model.Tag;
import lombok.AllArgsConstructor;

import console.app.model.Post;
import console.app.model.PostStatus;
import console.app.repository.PostRepository;
import console.app.repository.WriterRepository;
import console.app.service.PostService;

import java.util.List;

@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;
    private WriterRepository writerRepo;

    @Override
    public Post getById(Long id) throws Exception {
        return postRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Post post = getById(id);
        if (writerRepo.isContainsPost(post)) {
            throw new Exception("Can't delete post");
        }
        postRepo.delete(post);
    }

    @Override
    public List<Post> getAll() throws Exception {
        return postRepo.getAll();
    }

    @Override
    public void create(String content, List<Tag> tagList, PostStatus postStatus) throws Exception {
        Post post = new Post();
        post.setPostId(postRepo.getLastId() + 1);
        post.setContent(content);
        post.setTagList(tagList);
        post.setStatus(postStatus);
        postRepo.save(post);
    }

    @Override
    public void update(Long id, String content, List<Tag> tagList) throws Exception {
        Post post = new Post();
        post.setPostId(id);
        post.setContent(content);
        post.setTagList(tagList);
        postRepo.update(post);
    }

    @Override
    public void checkTag(Long id) throws Exception {
        postRepo.checkTag(id);
    }

    @Override
    public void checkEditPost(Long id) throws Exception {
        postRepo.checkEditPost(id);
    }
}
