package alice.ui;

import java.util.Scanner;

/**
 * Represents the user interface of ALICE program.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Creates a new user interface to be used by the program.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prompts and reads user input.
     *
     * @return the user input.
     */
    public String readUserInput() {
        // Prompts user
        System.out.print(" > ");

        return sc.nextLine();
    }

    /**
     * Asks the user a question and get user feedback.
     *
     * @param question question to ask the user.
     * @return the user response to the question.
     */
    public String getFeedback(String question) {
        System.out.println(question);
        displayLine();
        return readUserInput();
    }

    /**
     * Prints a divider to separate user input and program output.
     */
    public void displayLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a default welcome message.
     */
    public void displayWelcomeMsg() {
        String logo = " _____  _     _____ _____  _____\n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__\n" +
                "|  _  || |     | | | |    |  __|\n" +
                "| | | || |_____| |_| \\__/\\| |___\n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/\n";

        System.out.println(logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?");
    }

    /**
     * Prints a success message for successful file load.
     */
    public void displayLoadSuccess() {
        displayOutput("File successfully loaded!");
    }

    /**
     * Prints an error message for file load failure.
     *
     * @param filePath relative path to the data file.
     */
    public void displayLoadError(String filePath) {
        displayError("Cannot load/create file at " + filePath);
    }

    /**
     * Prints a warning message to the user.
     *
     * @param warningMessage warning message to print.
     */
    public void displayWarning(String warningMessage) {
        System.out.println("!! " + warningMessage);
    }

    /**
     * Prints an error message to the user.
     *
     * @param errorMessage error message to print.
     */
    public void displayError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    /**
     * Prints a message to the user.
     *
     * @param outputMessage message to print.
     */
    public void displayOutput(String outputMessage) {
        System.out.println(outputMessage);
    }
}
