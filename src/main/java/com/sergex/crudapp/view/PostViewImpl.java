package com.sergex.crudapp.view;

import com.sergex.crudapp.repository.TagRepository;
import com.sergex.crudapp.repository.gson.GsonTagRepositoryImpl;
import com.sergex.crudapp.utils.Messages;
import com.sergex.crudapp.model.Post;
import com.sergex.crudapp.model.PostStatus;
import com.sergex.crudapp.model.Tag;

import com.sergex.crudapp.controller.PostController;
import lombok.AllArgsConstructor;

import java.util.*;

public class PostViewImpl extends GeneralView {

    private PostController postController;
    private Scanner sc;

    public PostViewImpl(Scanner sc, PostController postController) {
        this.message = actionList;
        this.postController = postController;
        this.sc = sc;
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

    @Override
    public void show() {
        boolean isExit = false;
        while (true) {
            print();
            System.out.println(actionList);
            String response = sc.next();
            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println(Messages.ERROR_INPUT.getMessage());
                    break;
            }
            if (isExit)
                break;
        }
    }

    @Override
    public void create() {
        System.out.println(printActionList);
        String content = createContentPost();
        GsonTagRepositoryImpl gsonTagRepository =
                new GsonTagRepositoryImpl();
        List<Tag> tagList = gsonTagRepository.getAll();
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
        String content = sc.next();
        Post post = new Post();
        List<Tag> tagList = (List<Tag>) post.getTags();
        post = postController.getById(id);
        if (post.getStatus() == PostStatus.ACTIVE) {
            System.out.println(Messages.ACTIVE_STATUS.getMessage());
        }

        if (post.getStatus() == PostStatus.DELETED) {
            System.out.println(Messages.DELETED_STATUS.getMessage());
        }
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
        postList.sort(Comparator.comparing(Post::getId));
        System.out.println(printActionList);
        if (postList.size() != 0) {
            for (Post p : postList) {
                String printLine = p.getId() + ","
                        + p.getContent() + ","
                        + p.getTags() + ","
                        + p.getStatus();
                System.out.println(printLine);
            }
        } else {
            System.out.println(Messages.EMPTY_LIST.getMessage());
        }
    }

    private String createContentPost() {
        System.out.println(Messages.CONTENT.getMessage());
        String content = sc.next();
        return content;
    }
}
