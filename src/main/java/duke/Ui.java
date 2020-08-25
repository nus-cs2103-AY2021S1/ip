package duke;

import java.util.Scanner;

public class Ui {
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String indentation = "     ";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     * @return String of user input.
     */
    public String readInput() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints the greet message.
     */
    public void greet() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    /**
     * Prints the exit message and close the scanner.
     */
    public void exit() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(indentation + bye);
        System.out.println(horizontalLine);
        scanner.close();
    }

    /**
     * Prints the error message.
     * @param message Error message.
     */
    public void printErrorMessage(String message) {
        System.out.println(indentation + "☹ OOPS!!! " + message);
        System.out.println(horizontalLine);
    }

    /**
     * Prints a horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

}
