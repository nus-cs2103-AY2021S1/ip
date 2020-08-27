package duke;

import java.util.Scanner;


/**
 * Represents a user interface which displays messages to the user or
 * receives user input commands.
 */
public class Ui {

    /** Scanner object to scan for user input */
    private Scanner scanner;

    /**
     * Creates a user interface.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * Reads the user input from the user interface and generates a String.
     * @return The user input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param displayMessage The message to be displayed to the user.
     */
    public void display(String displayMessage) {
        System.out.println(displayMessage);
    }

    /**
     * Displays an error message to the user when an Exception or Error is thrown.
     *
     * @param errorMessage The error message to be displayed to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a goodbye message to the user when user exits Duke.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a line to the user to separate commands.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
