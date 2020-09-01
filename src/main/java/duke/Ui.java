package duke;

import java.util.Scanner;

/**
 * Ui class to handle the user input and user interface.
 * Includes method that will prompt the user for their input.
 */
public class Ui {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String showLine() {
        return ("________________________ \n");
    }

    /**
     * Display welcome message with duke logo to user.
     */
    public String showWelcomeMessage() {
        return "Hello from\n" + logo
                + "\nHello! I'm Duke \nWhat can I do for you?";
    }

    public String showExitMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    public String showErrorMessage(String message) {
        return message;
    }

    public String printMessage(String message) {
        return (message);
    }

}

