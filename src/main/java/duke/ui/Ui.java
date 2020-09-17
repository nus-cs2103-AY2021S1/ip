package duke.ui;

import java.util.Scanner;

/**
 * Represent the user interface for the Duke program.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
    private final String divider = "----------------------------------------------------------------------------------";
    /**
     * Print the divider line to the standard output.
     */
    public void showLine() {
        System.out.println(divider);
    }

    /**
     * Print the welcome logo to the standard output.
     */
    public void showWelcome() {
        System.out.println("\n" + LOGO);
        showLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    /**
     * Print to the standard output the result of user input.
     * @param output result after parsing user input
     */
    public void showOutput(String output) {
        showLine();
        System.out.println(output);
        showLine();
        System.out.println();
    }

    /**
     * Read input from user.
     * @return input string
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
