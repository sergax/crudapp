package console.app.repository.gson;

import console.app.model.Messages;
import console.app.model.Tag;
import console.app.writeAndRead.GReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import console.app.repository.TagRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class GsonTagRepositoryImpl implements TagRepository {

    private final static String FILE_NAME = "tags.txt";

    @Override
    public Tag getById(Long id) throws Exception {
        List<Tag> tags = stringToData(GReader.read(FILE_NAME));

        Tag current = null;
        for (Tag t : tags) {
            if (t.getTagId().equals(id)) {
                current = t;
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
        List<Tag> tags = stringToData(GReader.read(FILE_NAME));
        tags.sort(Comparator.comparing(Tag::getTagId));

        if (tags.size() != 0) {
            return tags.get(tags.size() - 1).getTagId();
        }
        return 0L;
    }

    @Override
    public void delete(Tag item) throws Exception {
        List<Tag> tags = stringToData(GReader.read(FILE_NAME));

        Tag removeTag = null;
        for (Tag t : tags) {
            if (t.getTagId().equals(item.getTagId())) {
                removeTag = t;
                break;
            }
        }
        tags.remove(removeTag);
        GReader.writeList(FILE_NAME,dataToString(tags));
    }

    @Override
    public void update(Tag item) throws Exception {
        delete(getById(item.getTagId()));
        save(item);
    }

    @Override
    public void save(Tag item) {
        GReader.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Tag> getAll() throws Exception {
        return stringToData(GReader.read(FILE_NAME));
    }

    @Override
    public List<Tag> stringToData(List<String> items) throws Exception {
        List<Tag> tags = new ArrayList<>();
        for (String str : items) {
            String[] parts = str.split(",");
            Tag tag = new Tag();
            tag.setTagId(Long.parseLong(parts[0]));
            tag.setTagName(parts[1]);
            tags.add(tag);
        }
        return tags;
    }

    @Override
    public List<String> dataToString(List<Tag> items) {
        List<String> data = new ArrayList<>();
        for (Tag t : items) {
            data.add(dataToString(t));
        }
        return data;
    }

    @Override
    public String dataToString(Tag tag) {
        return tag.getTagId() + "," + tag.getTagName();
    }
}
