package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     *
     * @return String of user input.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Returns the greet message.
     */
    public static String greet() {
        return ("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * Returns the exit message and close the scanner.
     */
    public String exit() {
        scanner.close();
        return getExitMessage();
    }

    public static String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the error message.
     *
     * @param message Error message.
     */
    public static String getErrorMessage(String message) {
        return "OOPS!!! " + message;
    }

}
