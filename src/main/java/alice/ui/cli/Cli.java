package alice.ui.cli;

import java.util.Scanner;

/**
 * Represents the client line interface of ALICE program.
 * Mainly used for testing purposes.
 */
public class Cli {
    private final Scanner sc;

    /**
     * Creates a new user interface to be used by the program.
     */
    public Cli() {
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
     * Prints a divider to separate user input and program output.
     */
    public void displayLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a default welcome message.
     */
    public void displayWelcomeMsg() {
        String logo = " _____  _     _____ _____  _____\n"
                + "/  _  \\| |   |_   _/  __ \\|  ___|\n"
                + "| |_| || |     | | | /  \\/| |__\n"
                + "|  _  || |     | | | |    |  __|\n"
                + "| | | || |_____| |_| \\__/\\| |___\n"
                + "\\_| |_/\\_____/\\___/ \\____/\\____/\n";

        System.out.println(logo
                + "\nHello! I'm Alice\n"
                + "How can I help you today?");
    }

    /**
     * Prints the file loading status message for initialisation of ALICE.
     */
    public void displayInitMessage(String msg) {
        displayOutput(String.format("<%s>", msg));
    }

    /**
     * Prints an error message to the user.
     *
     * @param errorMessage error message to print.
     */
    public void displayError(String errorMessage) {
        System.out.println("Oops! " + errorMessage);
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
