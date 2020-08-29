package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Utility class that handles interactions with the user.
 */
public class Ui {
    private Scanner sc;
    String nextMessage;

    /**
     * Initialises a new Ui object
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Show a greeting message to the user.
     */
    public static String getGreetingMessage() {
        String logo = ",--.                           ,--. \n"
                + "|  |-.  ,---. ,--.--. ,---.  ,-|  | \n"
                + "| .-. '| .-. ||  .--'| .-. :' .-. | \n"
                + "| `-' |' '-' '|  |   \\   --.\\ `-' | \n"
                + " `---'  `---' `--'    `----' `---'  \n\n";
        return "Hi I'm\n" + logo + "Please give me something to do.";
    }

    /**
     * Shows a farewell message to the user.
     */
    public void sayBye() {
        botOutput("Come back soon!! I'm always bored...");
    }

    /**
     * Shows an error message from a DukeException to the user.
     * @param e Exception containing the error message.
     */
    public void showError(DukeException e) {
        botOutput(e.getMessage());
    }
    
    public void botOutput(String message) {
        this.nextMessage = message;
    }

    public void botOutput(StringBuilder message) {
        botOutput(message.toString());
    }
    
    public String getNextMessage() {
        return this.nextMessage;
    }

    /**
     * Reads a command from the user.
     * @return Command read from the user.
     */
    public String readNextCommand() {
        return sc.nextLine();
    }
}
