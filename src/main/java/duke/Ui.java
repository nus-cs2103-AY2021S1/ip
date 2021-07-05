package duke;

import java.util.Scanner;

/**
 * Handles Duke's File I/O.
 */
public class Ui {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final String LINE = "------------------------------"
            + "------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints out the Duke logo.
     */
    public String getLogo() {
        return LOGO;
    }

    /**
     * Prints out the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Duke.\nWhat can I do for you?\n" + LINE;
    }

    /**
     * Scans the input line from System.in and returns it.
     *
     * @return String of trimmed input line.
     */
    public String readLine() {
        return INPUT_READER.nextLine();
    }

    /**
     * Prints out the input message to System.out.
     *
     * @param message message to be printed.
     */
    public String getLine(String message) {
        return message;
    }

    /**
     * Prints out the farewell message.
     */
    public String printBye() {
        return "Bye. Hope to see you again!";
    }

    /**
     * Prints out the farewell message.
     *
     * @param exception exception to be printed.
     */
    public String getError(Exception exception) {
        return exception.getMessage();
    }

}
