package com.sergex.crudapp;

import com.sergex.crudapp.utils.Messages;
import com.sergex.crudapp.view.ConsoleRunner;

public class ConsoleApp {

    public static void main(String[] args) {
        try {
            ConsoleRunner runner = new ConsoleRunner();
            runner.run();
        } catch (Exception e) {
            System.out.println();
        }
    }
}
