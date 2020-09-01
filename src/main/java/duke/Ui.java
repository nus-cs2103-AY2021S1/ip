package duke;

import java.util.Scanner;

/**
 * Represents the user interface that deals with interactions with the user.
 */
public class Ui {
    /** Scanner to take in user input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Public constructor.
     */
    public Ui() { }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMsg() {
        System.out.println("____________________________________________________________");
        System.out.println("Eh what's up! I'm Meimei" +
            "\nWhat you want ah?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads user inputs which should contain commands to the bot.
     *
     * @return the line inputted by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints lines to frame bot responses.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error messages from DukeExceptions thrown during
     * the bot execution.
     *
     * @param message message from the getMessage() method of a DukeException
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
