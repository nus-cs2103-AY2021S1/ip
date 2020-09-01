package util;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 * */
public class Ui {
    /** Constants **/
    private final String outputBreaker = ">>> ";
    private final String lineBreaker = "--.--.--.--.--.--.--.--.--.--.--." +
            "--.--.--.--.--.--.--.--.--.--.--";
    // Text Images
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Other variable
    private Scanner scanner;

    private String greetings = "";

    /**
     * Constructors.
     */
    public Ui() {
        // Using Scanner for reading inputs
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints Duke's greetings to user.
     */
    public void displayGreetings() {
        System.out.println("\nHello, I'm Duke!");
        System.out.println("What can I help you with today?");
        System.out.println("\n" + lineBreaker);
        System.out.println();
    }

    /**
     * Sets Duke's load message as a string.
     */
    public void setGreetings(String loadMessage) {
        this.greetings = loadMessage + "\n";
    }

    /**
     * Returns Duke's greetings as a string.
     *
     * @return Duke's greetings as a String
     */
    public String getGreetings() {
        return greetings +
                "\nHello, I'm Duke! " +
                "What can I help you with today?" +
                "\n" + lineBreaker + "\n";
    }

    /**
     * Print's Duke's farewell to user.
     */
    public void displayFarewells() {
        System.out.print(outputBreaker);
        System.out.println("Bye! Hoped I helped!");
        System.out.println("\n" + lineBreaker);
    }

    /**
     * Gets Duke's farewell to user.
     *
     * @return Duke's farewell as a String
     */
    public String getFarewells() {
        return outputBreaker +
                "Bye! Hoped I helped!" +
                "\n" + lineBreaker;
    }

    /**
     * Takes in input from user.
     *
     * @return User input as a String.
     */
    public String readUserInput() {
        // Read user input
        return scanner.nextLine();
    }

    /**
     * Prints Duke's symbols before response.
     */
    public void printOutputSymbol() {
        System.out.print(outputBreaker);
    }

    /**
     * Prints Duke's symbols before response.
     *
     * @return The string containing output symbols.
     */
    public String getOutputSymbol() {
        return lineBreaker + "\n" + outputBreaker;
    }

    /**
     * Prints a line.
     */
    public void printLineBreaker() {
        System.out.println(lineBreaker);
        System.out.println();
    }

    /**
     * Returns the line breaker string.
     *
     * @returns Line break as String.
     */
    public String getLineBreaker() {
        return lineBreaker + "\n";
    }

    /**
     * Prints error message for when Duke fails to load save file.
     */
    public void showLoadingError() {
        System.out.println("... Who? Never mind. Er-hmm.");
        System.out.println();
    }

    /**
     * Prints error messages from DukeException.
     *
     * @param e The DukeException to print message of.
     */
    public void printError(DukeException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    /**
     * Prints messages from commands.
     *
     * @param s The message to print.
     */
    public void printMessage(String s) {
        printOutputSymbol();
        System.out.println(s);
        System.out.println();
    }

    /**
     * Prints error messages from DukeException.
     *
     * @param e The DukeException to print message of.
     * @return String message of exception.
     */
    public String getError(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Prints all tasks within a TaskList (if any).
     * Else prints a list is empty message.
     *
     * @param list TaskList of Duke.
     */
    public void printList(TaskList list) {
        System.out.print("Here is what I have! ^^\n");
        if (list.isListEmpty()) {
            // Handles printing empty list
            System.out.println("Whoops! I don't have anything of note yet...");
        } else {
            list.printAllTasks();
        }
        System.out.println();
    }

    /**
     * Returns all tasks within a TaskList (if any).
     * Else prints a list is empty message.
     *
     * @param list TaskList of Duke.
     * @return List of tasks in String.
     */
    public String getList(TaskList list) {
        String output = "Here is what I have! ^^\n";
        if (list.isListEmpty()) {
            // Handles printing empty list
            output += "Whoops! I don't have anything of note yet...";
        } else {
            output += list.getAllTasks();
        }
        return output;
    }
}
