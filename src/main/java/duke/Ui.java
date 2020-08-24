package duke;

import java.util.Scanner;

/**
 * Handles Duke's File I/O.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private final String line = "------------------------------------------------------------";;

    /**
     * Prints out the Duke logo.
     */
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Prints out the welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(this.line);
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
    public void printLine(String message) {
        System.out.println(message);
        System.out.println(this.line);
    }

    /**
     * Prints out the farewell message.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Prints out the farewell message.
     *
     * @param exception exception to be printed.
     */
    public void printError(Exception exception) {
        this.printLine(exception.getMessage());
    }

}
