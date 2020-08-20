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

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("OOPS!!! I'm sorry, the file is not loaded correctly, please check its location");
    }

    /**
     * Returns a String used for output on GUI.
     * @param response The CommandResult representing the result of the command execution.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }
}
