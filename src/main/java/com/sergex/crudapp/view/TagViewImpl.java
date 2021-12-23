package com.sergex.crudapp.view;

import com.sergex.crudapp.controller.TagController;
import com.sergex.crudapp.utils.Messages;
import com.sergex.crudapp.model.Tag;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class TagViewImpl extends GeneralView {

    private TagController tagController;
    private Scanner sc;

    public TagViewImpl(TagController tagController, Scanner sc) {
        this.message = actionList;
        this.tagController = tagController;
        this.sc = sc;
    }

    private final String actionList = "Choose action by tags : \n" +
            "1. Create \n" +
            "2. Update \n" +
            "3. Delete \n" +
            "4. Get list \n" +
            "5. Exit \n";

    private final String printActionList = "List of tags : \n" + "ID; name";
    private final String createActionList = "Create tag . \n" + Messages.NAME.getMessage();
    private final String updateActionList = "Update tag . \n" + Messages.ID.getMessage();
    private final String deleteActionList = "Delete tag . \n" + Messages.ID.getMessage();

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
        String name = sc.next();
        try {
            tagController.create(name);
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
        System.out.println(Messages.NAME.getMessage());
        String name = sc.next();
        try {
            tagController.update(id,name);
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
            tagController.delete(id);
            System.out.println(Messages.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Messages.ERROR_OPERATION.getMessage());
        }
    }

    @Override
    public void print() {
        List<Tag> tags;
        try {
            tags = tagController.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        tags.sort(Comparator.comparing(Tag::getId));
        System.out.println(printActionList);
        if(tags.size() != 0) {
            for(Tag t : tags) {
                System.out.println(t.getId() + ";" + t.getName());
            }
        } else {
            System.out.println(Messages.EMPTY_LIST.getMessage());
        }
    }
}
