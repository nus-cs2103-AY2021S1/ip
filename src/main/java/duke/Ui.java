package duke;

import java.util.Scanner;

/**
 * User interaction class that is responsible for taking in user input as well as returning an output from Duke
 * to the user.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor to create a new Ui object, which will be used by Duke to interact with the user.
     */
    Ui() {
       this.sc = new Scanner(System.in);
    }

    /**
     * Awaits user input from the command line.
     *
     * @return a method to get user input from the command line.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a welcome message that is shown to the user.
     */
    public void showWelcome() {
        System.out.println("Hello from duke.Duke\nHow may I be of service " +
                "to you this fine day sire?");
    }

    /**
     * Prints a line divider for better formatting of Duke's responses.
     */
    public void showLine() {
        System.out.println("_____________________________________");
    }

    /**
     * Prints a error message to the user when Duke is unable to load Tasks from the user's local storage.
     *
     * @param errorMessage the message describing the details of the error.
     */
    public void showLoadingError(String errorMessage) {
        System.out.println("I am unable to load the saved tasks " + errorMessage);
    }

    /**
     * Prints an error message to the user when Duke encounters an error.
     *
     * @param errorMessage the message describing the details of the error.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a message to the user.
     *
     * @param message the message that Duke is sending to the user.
     */
    public void print(String message) {
        System.out.println(message);
    }
}
