package duke.ui;

import java.util.Scanner;

/**
 * Deals with user interactions.
 */
public class Ui {
    private final String WELCOME = "Hello. I am Claude! What may I do for you today?";
    private final String GOODBYE = "Goodbye! Hope to see you again soon!";
    private final String LINE = "______________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads command input by user.
     *
     * @return String to be processed by parser.
     */
    public String readCommand() {
        if (sc.hasNext()){
            return sc.nextLine();
        } else {
            return "";
        }
    }

    /**
     * Prints a line that serves as a divider.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println(WELCOME);
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints output from a Command.
     * @param s String to be printed.
     */
    public void showDetails(String s) {
        System.out.println(s);
    }

    /**
     * Prints error message from an Exception.
     * @param e Exception to be printed.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints error message from failing to load save file.
     */
    public void showLoadingError() {
        System.out.println("Failed to load from file. Initiating new instace.");
    }
}
