package com.sergex.crudapp.view;

import com.sergex.crudapp.utils.Messages;
import com.sergex.crudapp.controller.PostController;
import com.sergex.crudapp.controller.TagController;
import com.sergex.crudapp.controller.WriterController;
import lombok.Data;

import java.util.Scanner;

@Data
public class ConsoleRunner {

    GeneralView writerView;
    GeneralView postView;
    GeneralView tagView;

    private Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {
        try {
            TagController tagController = new TagController();
            PostController postController = new PostController();
            WriterController writerController = new WriterController();

            tagView = new TagViewImpl(tagController, sc);
            postView = new PostViewImpl(sc, postController);
            writerView = new WriterViewImpl(sc, writerController);

        } catch (Exception e) {
            System.err.println(Messages.DATA_DAMAGED.getMessage());
        }
    }

    public void run() {
            boolean isExit = false;
            while (true) {
                System.out.println(Messages.ACTION_LIST.getMessage());
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

    public static void main(String[] args) {
        ConsoleRunner runner = new ConsoleRunner();
        runner.run();
    }
}



