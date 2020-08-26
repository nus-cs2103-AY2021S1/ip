package main.java.duke;

import java.util.Scanner;

/**
 * Ui class to handle the user input and user interface.
 * Includes method that will prompt the user for their input.
 */
public class Ui {

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("________________________ \n");
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}

