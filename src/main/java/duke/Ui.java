package duke;

import java.util.Scanner;

/**
 * Represents the Ui.
 * Handles input and output to the client.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sends message to client.
     * @param message message to be sent to client
     */
    public void sendMessage(String message) {
        System.out.println(String.format("----------\n%s\n----------\n", message));
    }

    /**
     * Returns input from client.
     * @return input from client
     */
    public String getInput() {
        return this.scanner.nextLine();
    }

}
