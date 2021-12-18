package console.app.repository.gson;
import console.app.model.*;
import console.app.writeAndRead.GReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import console.app.repository.PostRepository;
import console.app.repository.TagRepository;

import java.util.*;

@AllArgsConstructor
public class GsonPostRepositoryImpl implements PostRepository {

    private TagRepository tagRepository;

    private final static String FILE_NAME = "posts.txt";
    private final String messageAboutDeletedStatusPost = "You can't edit, post was DELETE";
    private final String messageAboutActiveStatusPost = "You can't edit, post is ACTIVE";

    @Override
    public Post getById(Long id) throws Exception {
        List<Post> posts = stringToData(GReader.read(FILE_NAME));

        Post current = null;
        for (Post p : posts) {
            if (p.getPostId().equals(id)) {
                current = p;
                break;
            }
        }
        if (current != null) {
            return current;
        }
        throw new Exception(Messages.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public Long getLastId() throws Exception {
        List<Post> posts = stringToData(GReader.read(FILE_NAME));
        posts.sort(Comparator.comparing(Post::getPostId));

        if (posts.size() != 0) {
            return posts.get(posts.size() - 1).getPostId();
        }
        return 0L;
    }

    @Override
    public void delete(Post item) throws Exception {
        List<Post> posts = stringToData(GReader.read(FILE_NAME));
        Post removePost = null;
        for (Post p : posts) {
            if (p.getPostId().equals(item.getPostId())) {
                removePost = p;
                break;
            }
        }
        posts.remove(removePost);
        GReader.writeList(FILE_NAME, dataToString(posts));
    }

    @Override
    public void update(Post item) throws Exception {
        delete(getById(item.getPostId()));
        save(item);
    }

    @Override
    public void save(Post item) {
        GReader.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Post> getAll() throws Exception {
        return stringToData(GReader.read(FILE_NAME));
    }

    @Override
    public void checkTag(Long id) throws Exception {
        try {
            tagRepository.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void checkEditPost(Long id) throws Exception {
        Post post = getById(id);

        if (post.getStatus() == PostStatus.ACTIVE) {
            throw new Exception(messageAboutActiveStatusPost);
        }

        if (post.getStatus() == PostStatus.DELETED) {
            throw new Exception(messageAboutDeletedStatusPost);
        }
    }

    @Override
    public boolean isContainsTag(Tag tag) throws Exception {
        List<Post> posts = stringToData(GReader.read(FILE_NAME));
        for (Post p : posts) {
            if (p.getTagList().equals(tag)) return true;
        }
        return false;
    }

    @Override
    public List<Post> stringToData(List<String> items) throws Exception {
        List<Post> posts = new ArrayList<>();
        //id,content,list<tag>,status
        for (String str : items) {
            String[] parts = str.split(",");
            Post post = new Post();
            post.setPostId(Long.parseLong(parts[0]));
            post.setContent(parts[1]);
            String[] arr = parts[2].split(",");
            List<Tag> tags = new ArrayList<>();
            List<Long> array = new ArrayList<>();
            for (String s : arr) {
                Long tagId = Long.parseLong(s);
                array.add(tagId);
                tags.add(tagRepository.getById(tagId));
            }
            post.setStatus(PostStatus.valueOf(parts[3]));
            posts.add(post);
        }
        return posts;
    }

    @Override
    public List<String> dataToString(List<Post> items) {
        List<String> list = new ArrayList<>();
        for (Post p : items) {
            list.add(dataToString(p));
        }
        return list;
    }

    @Override
    public String dataToString(Post post) {
        String list = post.getPostId() + "," + post.getContent() + "," + post.getTagId() + "," + post.getStatus();
        return list;
    }
}
