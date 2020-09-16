package duke.duke;

import java.util.Scanner;

/**
 * Ui class handles the interactions between user and the program.
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads the next line provided by the user.
     *
     * @return The command provided as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Outputs the String provided by the program to the user.
     *
     * @param output The String intended to be outputted to the user.
     */
    public void showOutput(String output) {
        System.out.println(output);
    }

    /**
     * Welcome message String for the user.
     */
    public String showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\nWhat can I do for you?";
    }

    /**
     * Line separator for user experience.
     */
    public void showLine() {
        System.out.println("\n____________________________________________________________\n");
    }

    /**
     * Provides the user an understanding of what went wrong during program execution.
     *
     * @param errorMessage The error message to be shown to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
