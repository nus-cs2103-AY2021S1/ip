package main.java;

import java.util.Scanner;

/**
 * Represents a Ui which interacts with the user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays the welcome page of Duke for the user to see when starting the CLI application.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    /**
     * Displays a message to the user indicating that it couldn't retrieve any saved data.
     */
    public void showLoadingError() {
        System.out.println("Failed to load from saved data\n");
    }

    /**
     * Reads the input from the user.
     *
     * @return The input as a String from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a line seperator to the user.
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param err The error message from an exception.
     */
    public void showError(String err) {
        System.out.printf("\t%s\n", err);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to the displayed to the user.
     */
    public void showMessage(String message) {
        System.out.print(message);
    }
}
