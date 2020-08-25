package duke.common;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Generates and prints a custom string.
     * @param s text to be displayed to the user
     */
    public static void display(String s) {
        displayLine();
        System.out.println(s);
        displayLine();
    }

    /**
     * Generates and prints a divider line.
     */
    public static void displayLine() {
        System.out.println("________________________________________");
    }

    /**
     * Generates and prints the current task list of the user.
     * @param tasks list of tasks the user has inputted thus far
     */
    public static void displayTasks(ArrayList<Task> tasks) {
        displayLine();
        System.out.println("Here is your current list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        displayLine();
    }

    public static void displayMatchingTasks(ArrayList<Task> tasks) {
        displayLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        displayLine();
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }

    /**
     * Uses scanner to read the next line that the user input.
     * @return next command
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
