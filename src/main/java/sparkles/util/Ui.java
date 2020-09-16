package sparkles.util;

import java.util.Scanner;

/**
 * A Ui object that deals with user interactions.
 */
public class Ui {

    private static final String DASH = "     ____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message of Sparkles.
     */
    public void showWelcome() {
        showLine();
        print("     *Hello, I am Sparkles*\n\n     How can I help you?");
        showLine();
    }

    public void showLine() {
        print(DASH);
    }

    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Print the number of task in the list now.
     *
     * @param i number of task
     * @return a string displaying size of list
     */
    public String showListSize(int i) {
        print("     Now you have " + i + " task(s) in your list.");
        return "Now you have " + i + " task(s) in your list.";
    }

    /**
     * Returns user's direct input.
     *
     * @return user's next command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        print("     File not loaded");
    }

    public void showError(String message) {
        print(message);
    }


}
