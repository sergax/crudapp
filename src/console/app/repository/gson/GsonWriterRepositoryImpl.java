package console.app.repository.gson;

import console.app.model.*;
import console.app.writeAndRead.GReader;
import lombok.AllArgsConstructor;
import console.app.repository.PostRepository;
import console.app.repository.WriterRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class GsonWriterRepositoryImpl implements WriterRepository {

    private PostRepository postRepository;

    private final static String FILE_NAME = "writers.txt";
    private final String messageAboutDeletedStatusPost = "You can't edit, post was DELETE";
    private final String messageAboutActiveStatusPost = "You can't edit, post is ACTIVE";

    @Override
    public Writer getById(Long id) throws Exception {
        List<Writer> writers = stringToData(GReader.read(FILE_NAME));
        Writer current = null;
        for (Writer w : writers) {
            if (w.getWriterId().equals(id)) {
                current = w;
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
        List<Writer> writers = stringToData(GReader.read(FILE_NAME));
        writers.sort(Comparator.comparing(Writer::getWriterId));

        if (writers.size() != 0) {
            return writers.get(writers.size() - 1).getWriterId();
        }
        return null;
    }

    @Override
    public void delete(Writer item) throws Exception {
        List<Writer> writers = stringToData(GReader.read(FILE_NAME));

        Writer removeWriter = null;
        for (Writer w : writers) {
            if (w.getWriterId().equals(item.getWriterId())) {
                removeWriter = w;
                break;
            }
        }
        writers.remove(removeWriter);
        GReader.writeList(FILE_NAME, dataToString(writers));
    }

    @Override
    public void update(Writer item) throws Exception {
        delete(getById(item.getWriterId()));
        save(item);
    }

    @Override
    public void save(Writer item) {
        GReader.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Writer> getAll() throws Exception {
        return stringToData(GReader.read(FILE_NAME));
    }

    @Override
    public List<Writer> stringToData(List<String> items) throws Exception {
        List<Writer> writers = new ArrayList<>();
        for (String str : items) {
            String[] parts = str.split(",");
            Writer writer = new Writer();
            writer.setWriterId(Long.parseLong(parts[0]));
            writer.setWriterName(parts[1]);
            String[] arr = parts[2].split(",");
            List<Post> posts = new ArrayList<>();
            List<Long> array = new ArrayList<>();
            for (String s : arr) {
                Long postId = Long.parseLong(s);
                array.add(postId);
                posts.add(postRepository.getById(postId));
            }
            writers.add(writer);
        }
        return writers;
    }

    @Override
    public List<String> dataToString(List<Writer> items) {
        List<String> data = new ArrayList<>();
        for (Writer w : items) {
            data.add(dataToString(w));
        }
        return data;
    }

    @Override
    public String dataToString(Writer writer) {
        String data = writer.getWriterId() + "," + writer.getWriterName() + writer.getPostId();
        return data;
    }

    @Override
    public boolean isContainsPost(Post post) throws Exception {
        List<Writer> writers = stringToData(GReader.read(FILE_NAME));
        for (Writer w : writers) {
            if(w.getPostList().contains(post)) return  true;
        }
        return false;
    }

    @Override
    public void checkPost(Long id) throws Exception {
        try {
            postRepository.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
