package com.sergex.crudapp.utils;

public enum Messages {

    DELETED_STATUS("You can't edit, post was DELETE"),
    ACTIVE_STATUS("You can't edit, post is ACTIVE"),
    ERROR_INPUT("Wrong input, try again pleas"),
    EMPTY_LIST("List is empty"),
    SUCCESSFUL_OPERATION("Successful operation"),
    ERROR_OPERATION("Error!!!"),
    NAME("Put your name : "),
    ID("Put your ID : "),
    NOT_FIND_ID("Not found ID "),
    CONTENT("Put your content : ");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
