package util;

import java.util.Scanner;

/**
 * API to read user input and print standard messages.
 */
public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String line = "________________________________________";

    private Scanner sc;

    /**
     * Creates new UI object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome statement.
     */
    public void printWelcome() {
        System.out.print(getWelcome());
    }

    public String getWelcome() {
        return "Hello from Duke\nWhat can I do for you?\n" + line + "\n";
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println(line);
    }

    public String getLine() {
        return line + "\n";
    }

    /**
     * Reads a line from the user.
     */
    public String readLine() {
        return sc.nextLine();
    }
}
