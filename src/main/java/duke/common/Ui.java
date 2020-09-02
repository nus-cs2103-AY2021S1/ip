package duke.common;

import java.util.Scanner;

import duke.Main;
import duke.task.TaskList;

/**
 * Text UI of the application.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private static void displayString(String s) {
        Main.getWindow().displayText(s);
    }

    /**
     * Generates and prints a custom string.
     * @param s text to be displayed to the user.
     */
    public static void display(String s) {
        displayString(s);
    }


    /**
     * Generates and prints the current task list of the user.
     * @param tasks list of tasks the user has inputted thus far.
     */
    public static void displayTasks(TaskList tasks) {
        StringBuilder s = new StringBuilder();
        s.append("Here is your current list: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            s.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        displayString(s.toString());
    }

    /**
     * Generates and prints the remaining task list of the user.
     * @param tasks list of tasks the user has inputted thus far.
     */
    public static void displayRemainingTasks(TaskList tasks) {
        display("\nNow you have "
                + tasks.getSize()
                + " tasks in your list.");
    }

    /**
     * Generates and prints the matching task list of the user.
     * @param tasks list of tasks that matches the users keyword.
     */
    public static void displayMatchingTasks(TaskList tasks) {
        StringBuilder s = new StringBuilder();
        s.append("Here is the matching list: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            s.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        displayString(s.toString());

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

        displayString("Hello from\n" + logo);
        displayString("Hello! I'm Duke\n"
                + "     What can I do for you?");
    }

    /**
     * Uses scanner to read the next line that the user input.
     * @return next command.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
