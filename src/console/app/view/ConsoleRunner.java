package console.app.view;

import console.app.model.Messages;
import console.app.repository.gson.GsonPostRepositoryImpl;
import console.app.repository.gson.GsonTagRepositoryImpl;
import console.app.repository.gson.GsonWriterRepositoryImpl;
import console.app.service.PostService;
import console.app.service.TagService;
import console.app.service.WriterService;
import console.app.service.impl.PostServiceImpl;
import console.app.service.impl.TagServiceImpl;
import console.app.service.impl.WriterServiceImpl;
import console.app.controller.PostController;
import console.app.controller.TagController;
import console.app.controller.WriterController;
import console.app.repository.PostRepository;
import console.app.repository.TagRepository;
import console.app.repository.WriterRepository;
import lombok.Data;

import java.util.Scanner;

@Data
public class ConsoleRunner {

    GeneralView writerView;
    GeneralView postView;
    GeneralView tagView;

    private final String messageDataDamaged = "Data is damaged";
    private final String actionList = """
            Create actions :\s
            1. Tags\s
            2. Posts\s
            3. Writers\s
            4. Exit\s
            """;

    private Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {
        try {
            TagRepository tagRepo = new GsonTagRepositoryImpl();
            PostRepository postRepo = new GsonPostRepositoryImpl(tagRepo);
            WriterRepository writerRepo = new GsonWriterRepositoryImpl(postRepo);

            TagService tagService = new TagServiceImpl(tagRepo, postRepo);
            PostService postService = new PostServiceImpl(postRepo, writerRepo);
            WriterService writerService = new WriterServiceImpl(writerRepo);

            TagController tagController = new TagController(tagService);
            PostController postController = new PostController(postService);
            WriterController writerController = new WriterController(writerService);

            tagView = new TagViewImpl(tagController, sc);
            postView = new PostViewImpl(postController, sc,tagView);
            writerView = new WriterViewImpl(writerController,sc,postView);

        } catch (Exception e) {
            System.err.println(messageDataDamaged);
        }
    }

    public void run() {
        boolean isExit = false;
        while (true) {
            System.out.println(actionList);
            String response = sc.next();
            switch (response) {
                case "1":
                    tagView.show();
                    break;
                case "2":
                    postView.show();
                    break;
                case "3":
                    writerView.show();
                    break;
                case "4":
                    isExit = true;
                    break;
                default:
                    System.out.println(Messages.ERROR_INPUT.getMessage());
                    break;
            }
            if (isExit)
                break;
        }
        sc.close();
    }
}

