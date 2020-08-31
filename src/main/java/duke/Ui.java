package duke;

import java.util.Scanner;

import duke.exception.UiMessageException;

public class Ui {

    private static final Scanner sc = new Scanner(System.in);
    private String message;

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
    }

    /**
     * Set's message in UI to input message
     * @param message To over write UI's message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the String message embedded in UI
     * @return String representation of message
     * @throws UiMessageException Thrown when message is empty
     */
    public String getMessage() throws UiMessageException {
        if (message == null || message.equals("")) {
            throw new UiMessageException("Message is empty for UI! Please setMessage");
        } else {
            return message;
        }
    }

    /**
     * Clears message found in UI
     */
    public void clearMessage() {
        this.setMessage("");
    }

    /**
     * Reads User's input via stdin
     * @return User's input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints error message provided by exception
     * @param errorMessage Error Message provided by exception
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a line that is 30 characters long
     */
    public void showLine() {
        System.out.println("______________________________");
    }
}
