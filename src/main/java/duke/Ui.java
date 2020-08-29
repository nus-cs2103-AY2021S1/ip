package duke;

import java.util.Scanner;

/**
 * Front facing interface for printing and accepting of user input.
 */
public class Ui {
    private Scanner scannerObj;

    public Ui () {
        this.scannerObj = new Scanner(System.in);
    }

    static String MSG_DIVIDER = "____________________________________________________________";

    /**
     * Shows a LoadingError message to the user.
     */
    public void showLoadingError() {
        System.out.println("Oops! We couldn't load the file");
    }

    /**
     * Prints a line divider.
     */
    public void showLine() {
        System.out.println(MSG_DIVIDER);
    }

    /**
     * Shows a error message to the user.
     *
     * @param err description of the error
     */
    public void showError(String err) {
        System.out.println("Error: " + err);
    }

    /**
     * Shows a message to the user.
     *
     * @param msg the message to be printed.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello I'm batman");
    }

    /**
     * Shows a farewell message to the user.
     */
    public void showFarewell() {
        System.out.println("byeee");
    }

    /**
     * Reads the user's input.
     * @return the user's input as a string
     */
    public String readCommand() {
        String input = scannerObj.nextLine();
        return input;
    }
}