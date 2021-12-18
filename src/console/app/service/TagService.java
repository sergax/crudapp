package console.app.service;

import console.app.model.Tag;

public interface TagService extends GenericService<Tag, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;
}
