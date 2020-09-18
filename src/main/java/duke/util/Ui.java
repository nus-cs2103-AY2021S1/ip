package duke.util;

import java.util.Scanner;

/**
 * Deals with user interaction (input and output).
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public String printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return print("Hello from\n" + logo + "Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Helps print any message to standard out.
     * @param message Message to be printed.
     */
    public String print(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Obtains a line of input from user and trims whitespace.
     * @return Input as a string.
     */
    public String readInput() {
        return scanner.nextLine().trim();
    }
}
