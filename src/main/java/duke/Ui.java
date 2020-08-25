package main.java.duke;

import main.java.duke.task.Task;

import java.util.Scanner;

/**
 * Represents a group of methods that deal with interactions with the user.
 */
public class Ui {
    private static final String horizontalLine = "      ===================================";
    private static final String indentation = "      ";
    private Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Takes in one line of input from the user.
     *
     * @return the user's input
     * @throws DukeException if there is no more input
     */
    String readCommand() throws DukeException {
        if (this.scanner.hasNext()) {
            return scanner.nextLine().trim();
        } else {
            throw new DukeException("No next line");
        }
    }

    /**
     * Prints out a welcome message for the user.
     */
    public void showWelcome() {
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello from\n" + logo);
        System.out.println(horizontalLine);
    }

    /**
     * Prints out a goodbye message for the user.
     */
    public void showGoodbye() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Have a nice day.");
        System.out.println(horizontalLine);
    }

    /**
     * Prints out an error that occurred.
     *
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Error: " + message);
        System.out.println(horizontalLine);
    }

    /**
     * Prints out a message telling the user that a task was added.
     *
     * @param task the <code>Task</code> added
     */
    public void showTaskAdded(Task task) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Task added: " + task);
        System.out.println(horizontalLine);
    }

    /**
     * Prints out a message telling the user that a task was deleted.
     *
     * @param task the <code>Task</code> deleted
     */
    public void showTaskDeleted(Task task) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Task deleted: " + task);
        System.out.println(horizontalLine);
    }

    /**
     * Prints out a message telling the user that a task was marked as done.
     *
     * @param task the <code>Task</code> marked as done
     */
    public void showTaskMarkedDone(Task task) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Task marked as done: " + task);
        System.out.println(horizontalLine);
    }

    /**
     * Prints out a message to the user with the correct indentation.
     *
     * @param message the message to print
     */
    public void showIndentedMessage(String message) {
        System.out.println(indentation + message);
    }

    /**
     * Prints out an indented horizontal line.
     */
    public void showHorizontalLine() {
        System.out.println(horizontalLine);
    }
}
