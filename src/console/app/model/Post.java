package console.app.model;

import lombok.Data;

import java.util.List;

@Data
public class Post {
    private Long postId;
    private String content;
    private Long tagId;
    private List<Tag> tagList;
    private PostStatus status;
}
