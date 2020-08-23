package duke;

import java.util.Scanner;

public class Ui {

    private final static Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
    }

    /**
     * Reads User's input via stdin
     * @return User's input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints error message provided by exception
     * @param errorMessage Error Message provided by exception
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a line that is 30 characters long
     */
    public void showLine() {
        System.out.println("______________________________");
    }
}