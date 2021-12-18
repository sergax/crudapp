package console.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Writer {
    private Long writerId;
    private String writerName;
    private Long postId;
    private List<Post> postList;
    private PostStatus status;
}
