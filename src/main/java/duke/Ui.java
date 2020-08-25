package duke;

import java.util.Scanner;

/**
 * Handles the interaction with the user.
 */
public class Ui {

    /**
     * Uses Scanner to take in user input
     *
     * @return user input as a String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Greeting messages when bot starts up
     */
    public void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Goodbye messages upon bot exits
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints messages to the user
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints the error message
     * @param error error message
     */
    public void showError(String error) {
        System.out.println(error);
    }
}
