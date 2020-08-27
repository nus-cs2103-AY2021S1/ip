package duke;

import java.util.Scanner;

/**
 * An ui to send message to user.
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Sends a welcoming message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("        Hello! I'm duke.Duke\n" + "        What can I do for you?");
        showLine();
    }

    /**
     * Reads a line from the terminal and return it as a string.
     * @return command line detected
     */
    public String readCommand() { return scanner.nextLine(); }

    /**
     * Prints a line to alert failure to load.
     */
    public void showLoadingError() {
        System.out.println("        Failed to load");
    }

    /**
     * Standard spacing line used for duke interactions.
     */
    public void showLine() { System.out.println("        ____________________________________________________________"); }

    /**
     * Sends a message with fixed spacing before the string.
     * @param msg Message to be sent
     */
    public void printMessage(String msg) {
        System.out.println("        " + msg);
    }

    /**
     * Sends an error message with the standard spacing in front.
     * @param errorMsg Error message to be sent
     */
    public void showError(String errorMsg) { System.out.println("        " + errorMsg); }
}
