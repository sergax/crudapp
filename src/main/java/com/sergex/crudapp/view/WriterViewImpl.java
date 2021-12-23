package com.sergex.crudapp.view;

import com.sergex.crudapp.controller.WriterController;
import com.sergex.crudapp.repository.gson.GsonPostRepositoryImpl;
import com.sergex.crudapp.utils.Messages;
import com.sergex.crudapp.model.Post;
import com.sergex.crudapp.model.Writer;

import java.util.*;

public class WriterViewImpl extends GeneralView {

    private WriterController writerController;
    private Scanner sc;

    public WriterViewImpl(Scanner sc, WriterController writerController) {
        this.message = actionList;
        this.writerController = writerController;
        this.sc = sc;
    }

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
                System.out.println(exitMessage);
                break;
        }
    }

    @Override
    public void create() {
        String name = createWriterName();
        GsonPostRepositoryImpl gsonPostRepository =
                new GsonPostRepositoryImpl();
        List<Post> postList = gsonPostRepository.getAll();
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
        Writer w = new Writer();
        List<Post> postList = w.getPosts();
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
        writers.sort(Comparator.comparing(Writer::getId));
        System.out.println(printActionList);
        if (writers.size() != 0) {
            for (Writer w : writers) {
                String printLine = w.getId() + ";"
                        + w.getId() + ";"
                        + w.getPosts();
                System.out.println(printLine);
            }
        } else {
            System.out.println(Messages.EMPTY_LIST.getMessage());
        }
    }

    private String createWriterName() {
        System.out.println(Messages.NAME.getMessage());
        return sc.next();
    }
}
