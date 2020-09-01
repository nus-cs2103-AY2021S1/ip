package duke;

import java.util.Scanner;

/**
 * Handles Duke's File I/O.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private final String line = "------------------------------"
            + "------------------------------";
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints out the Duke logo.
     */
    public String getLogo() {
        return this.logo;
    }

    /**
     * Prints out the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Duke.\nWhat can I do for you?\n" + this.line;
    }

    /**
     * Scans the input line from System.in and returns it.
     *
     * @return String of trimmed input line.
     */
    public String readLine() {
        return this.sc.nextLine();
    }

    /**
     * Prints out the input message to System.out.
     *
     * @param message message to be printed.
     */
    public String getLine(String message) {
        return message;// + "\n" + this.line;
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
