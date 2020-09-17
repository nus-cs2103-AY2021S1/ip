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

    private String helpMessage = "Looks like you need some help!\n"
            + "Use the following commands:\n"
            + "- todo (task)\n"
            + "- deadline (task) /by yyyy-mm-dd HH:mm\n"
            + "- event (task) /at yyyy-mm-dd HH:mm\n"
            + "- delete (task number)\n"
            + "- done (task number)\n"
            + "- list\n"
            + "- bye";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Display welcome message with duke logo to user.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Duke \nWhat can I do for you?\n"
                + "Use the command 'help' for more information!";
    }

    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showErrorMessage(String message) {
        return message;
    }

    public String showHelpMessage() {
        return helpMessage;
    }

    public String printMessage(String message) {
        return (message);
    }

}

