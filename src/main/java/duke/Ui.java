package duke;

import java.util.Scanner;

/** User interface of the chat bot */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a welcome message in the chat bot.
     * @return The greeting String.
     */
    public static String greet() {
        return "Hello! I'm Jarvis\n"
                + "What can I do for you?";
    }

    /**
     * Prints welcome message in the chat bot.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Jarvis.\n"
                + "What can I do for you?");
    }

    /**
     * Prints a separating line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads in a command input from user.
     *
     * @return A string representation of the command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The error message generated.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the loading error.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! I'm sorry, the file is not loaded correctly, please check its location");
    }

    /**
     * Returns a response to the user if no error occurs.
     *
     * @param response The response representing the result of the command execution.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }
}
