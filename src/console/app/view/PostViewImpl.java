package console.app.view;

import console.app.model.*;

import console.app.controller.PostController;

import java.util.*;

public class PostViewImpl extends GeneralView {

    private PostController postController;
    private GeneralView tagView;

    public PostViewImpl(PostController postController, Scanner sc, GeneralView tagView) {
        this.postController = postController;
        this.sc = sc;
        this.tagView = tagView;
    }

    private final String actionList = "Choose action by posts : \n" +
            "1. Create \n" +
            "2. Update \n" +
            "3. Delete \n" +
            "4. Get list \n" +
            "5. Exit \n";

    private final String printActionList = "List of posts : \n" + "ID; content; Tags; status";
    private final String createActionList = "Create post . \n" + Messages.CONTENT.getMessage();
    private final String updateActionList = "Update post . \n" + Messages.ID.getMessage();
    private final String deleteActionList = "Delete post . \n" + Messages.ID.getMessage();
    private final String addSameTagMessage = "The same tag, choose another one...\n" +
            "ID = ";
    private final String wantAddTagMessage = "Do you want add more tag ? (y/n):";
    private final String answerYes = "y";

    @Override
    public void create() {
        System.out.println(printActionList);
        String content = createContentPost();
        Post post = new Post();
        List<Tag> tagList = (List<Tag>) post.getTagList();
        try {
            postController.create(content, tagList, PostStatus.ACTIVE);
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(updateActionList);
        Long id = sc.nextLong();
        try {
            postController.checkEditPost(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(Messages.ERROR_OPERATION.getMessage());
            return;
        }
        String content = sc.next();
        Post post = new Post();
        List<Tag> tagList = (List<Tag>) post.getTagList();
        try {
            postController.update(id, content, tagList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void delete() {
        System.out.println(deleteActionList);
        Long id = sc.nextLong();
        try {
            postController.delete(id);
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void print() {
        List<Post> postList;
        try {
            postList = postController.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        postList.sort(Comparator.comparing(Post::getTagId));
        System.out.println(printActionList);
        if (postList.size() != 0) {
            for (Post p : postList) {
                String printLine = p.getTagId() + "," + p.getContent() + "," + p.getTagList() + "," + p.getStatus();
                System.out.println(printLine);
            }
        } else {
            System.out.println(Messages.EMPTY_LIST.getMessage());
        }
    }

    private List<Tag> chooseTags() {
        List<Tag> tags = new ArrayList<>();
        List<Long> tagList = new ArrayList<>();
        while (true) {
            tagView.print();
            System.out.println(Messages.ID.getMessage());
            Long tagId = sc.nextLong();
            try {
                postController.checkTags(tagId);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return tags;
    }

    private String createContentPost() {
        System.out.println(Messages.CONTENT.getMessage());
        String content = sc.next();
        return content;
    }
}
