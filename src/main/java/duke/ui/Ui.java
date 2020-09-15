package duke.ui;

import duke.exception.DukeException;

/**
 * Utility class that handles interactions with the user.
 */
public class Ui {
    private String nextMessage;

    /**
     * Show a greeting message to the user.
     */
    public static String getGreetingMessage() {
        return "Hi I'm\n" + "ｂｏｒｅｄ\n" + "Please give me something to do.";
    }

    /**
     * Shows a farewell message to the user.
     */
    public void sayBye() {
        botOutput("Come back soon!! I'm always bored...");
    }

    /**
     * Shows an error message from a {@link DukeException} to the user.
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
}
