package duke;

import java.util.Scanner;

/**
 * The Ui class will deal with interactions with the user.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Shows the Duke logo and welcomes the user.
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
     * Scans the entire line of text which is inputted by user.
     *
     * @return a String which is an exact copy of the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out the error message.
     *
     * @param message error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints out the error message in the event where file
     * could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error: File cannot be loaded");
    }

    /**
     * Prints out a line to mark the start and end of each command.
     */
    public void showLine() {
        System.out.println("___________________________________________________");
    }

    /**
     * Closes the scanner when the program exits.
     */
    public void closeScanner() {
        sc.close();
    }
}