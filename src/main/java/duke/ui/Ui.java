package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface class.
 */
public class Ui {
    private final Scanner scanner;
    private static final String DIVIDER = "---------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Construtor method.
     * Initialises a Scanner object that reads in input from the user.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a blank line.
     */
    public void showBlankLine() {
        System.out.println();
    }

    /**
     * Prints the starting message of Duke.
     */
    public void showStartMessage() {
        String message = "Greetings, what may I do for you?";
        System.out.println(LOGO + message);
    }

    /**
     * Prints out the divider line in between responses from Duke.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the message from Duke, after a user has input a command.
     * @param msg the message to be printed.
     */
    public void showStatus(String msg) {
        System.out.println(msg);
    }

    /**
     * Reads in the user input as a line.
     * @return the String of the user input.
     */
    public String readUserInput() {
        return this.scanner.nextLine();
    }
}
