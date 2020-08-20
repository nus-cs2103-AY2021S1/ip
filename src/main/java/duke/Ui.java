package duke;

import java.util.Scanner;

/**
 * User interface of the chat bot
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Print welcome message in the chat bot.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Jarvis\n"
                + "What can I do for you?");
    }

    /**
     * Print a separating line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Read in a command input from user.
     *
     * @return a string representation of the command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print the error message.
     *
     * @param errorMessage the error message generated.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Print the loading error.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! I'm sorry, the file is not loaded correctly, please check its location");
    }

    /**
     * Returns a response to the user if no error occurs.
     *
     * @param response the response representing the result of the command execution.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }
}
