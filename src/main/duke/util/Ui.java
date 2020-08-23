package duke.util;

import java.util.Scanner;

public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String line = "________________________________";

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
        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println(line);
    }

    /**
     * Reads a line from the user.
     */
    public String readLine() {
        return sc.nextLine();
    }
}
