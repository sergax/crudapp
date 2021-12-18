package console.app.view;

import console.app.controller.WriterController;
import console.app.model.Messages;
import console.app.model.Post;
import console.app.model.Tag;
import console.app.model.Writer;
import console.app.repository.PostRepository;

import java.util.*;


public class WriterViewImpl extends GeneralView {

    private WriterController writerController;
    private Scanner sc;
    private PostViewImpl postView;
    private TagViewImpl tagView;
    private PostRepository postRepository;

    private final String actionList = "Create action by writers : \n" +
            "1. Create \n" +
            "2. Update \n" +
            "3. Delete \n" +
            "4. Get list \n" +
            "5. Exit \n";

    private final String printActionList = "List of writers : \n" + "ID; name; Posts";
    private final String createActionList = "Create writer . \n" + Messages.NAME.getMessage();
    private final String updateActionList = "Update writer . \n" + Messages.ID.getMessage();
    private final String deleteActionList = "Delete writer . \n" + Messages.ID.getMessage();
    private final String exitMessage = "Exit .\n" + Messages.ID.getMessage();


    private final String addPostMessage = "Post already exists \n" +
            "ID = ";
    private final String wantAddPostMessage = "Do you want add a new Post :";
    private final String answerYes = "y";

    public WriterViewImpl(WriterController writerController, Scanner sc, GeneralView postView) {
        super();
    }

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
        System.out.println(createActionList);
        String name = createWriterName();
        Writer w = new Writer();
        List<Post> postList = w.getPostList();
        try {
            writerController.create(name, postList);
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(updateActionList);
        Long id = sc.nextLong();
        String name = createWriterName();
        List<Long> postList = choosePosts();
        try {
            writerController.update(id,name,postList);
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void delete() {
        System.out.println(deleteActionList);
        Long id = sc.nextLong();
        try {
            writerController.delete(id);
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void print() {
        List<Writer> writers;
        try {
            writers = writerController.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        writers.sort(Comparator.comparing(Writer::getWriterId));
        System.out.println(printActionList);
        if (writers.size() != 0) {
            for (Writer w : writers) {
                String printLine = w.getWriterId() + ";"
                        + w.getWriterId() + ";"
                        + w.getPostList();
                System.out.println(printLine);
            }
        } else {
            System.out.println(Messages.EMPTY_LIST.getMessage());
        }
    }


    private List<Long> choosePosts() {
        List<Long> postList = new ArrayList<>();
        while (true) {
            postView.print();
            System.out.println(Messages.ID.getMessage());
            Long postId = sc.nextLong();
            try {
                writerController.checkPost(postId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (postList.contains(postId)) {
                System.out.println(addPostMessage + postId);
            } else {
                postList.add(postId);
            }
            System.out.println(wantAddPostMessage);
            String response = sc.next();
            if (!response.equals(answerYes)) {
                break;
            }
        }
        return postList;
    }

    private String createWriterName() {
        System.out.println(Messages.NAME.getMessage());
        return sc.next();
    }
}
