package main.java.duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user by displaying messages to user.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________";
    private final String LOGO = " ____        ____\n"
            + "|  _ \\  ___ |  _ \\\n"
            + "| | | |/ _ \\| | | |\n"
            + "| |_| || __/| |_| |\n"
            + "|____/ \\___||____/\n";
    private Scanner sc;

    /**
     * Creates an Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome messages to user when Duke starts up.
     */
    public void showWelcome() {

        System.out.println("Hello I am\n" + LOGO + "\n" + "Feed me some stuff! :3\n");
    }

    /**
     * Reads user command line input and returns that line as a string.
     *
     * @return String of user input.
     */
    public String readCommand() {

        String command = sc.nextLine();
        return command;

    }

    /**
     * Displays error message to user.
     *
     * @param message Error message to be displayed to user.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Displays message to user.
     *
     * @param message Message to be displayed to user.
     */
    public void printMessage(String message) {
        System.out.println(String.format(message));
    }

}
