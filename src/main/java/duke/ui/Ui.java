package duke.ui;

import java.util.Scanner;

/**
 * Represents an interface to interact with the users. A <code>Ui</code>
 * object is responsible for getting inputs from the users and displaying
 * outputs.
 */
public class Ui {

    /** String to separate different outputs from Duke */
    private static final String LINE = "\t____________________________________________________________";

    /**
     * Constructs an <code>Ui</code> object.
     */
    public Ui() {
    }

    /**
     * Sends a greeting to users.
     */
    public void sendGreeting() {
        String logo = " ____        _        \n\t "
                + "|  _ \\ _   _| | _____ \n\t "
                + "| | | | | | | |/ / _ \\\n\t "
                + "| |_| | |_| |   <  __/\n\t "
                + "|____/ \\__,_|_|\\_\\___|\n\t ";
        System.out.println(LINE
                + "\n\t "
                + logo
                + "\n\t Hello! I'm Duke\n\t What can I do for you?\n"
                + LINE);
    }

    /**
     * Displays a LINE.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns a user input using a Scanner object.
     *
     * @return User Input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays error message of an Exception.
     *
     * @param e Exception that occurred.
     */
    public void showError(Exception e) {
        System.out.println("\t " + e.getMessage());
    }

    /**
     * Displays messages.
     *
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        System.out.println("\t " + message);
    }

}
