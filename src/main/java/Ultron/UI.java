package ultron;

import java.util.Scanner;

public class UI {

    /**
     * Store the scanner object.
     */
    private final Scanner scanner;

    /**
     * UI to handle all the displaying.
     */
    public UI() {

        //Create a new scanner object
        scanner = new Scanner(System.in);
    }

    /**
     * Show the error as output to System out.
     * @param message message to be shown
     */
    public void showError(final String message) {
        //Wrapper when printing the error message
        System.out.println("Heh, you can't even type in a correct command\n"
                + message
                + "\nI'll give u a pity tip\n"
                + "Use 'help' for more information");
    }

    /**
     * Get a line of input from system in.
     * @return String input received
     */
    public String getInput() {

        //Declare type
        String input;

        // Take in input
        input = this.scanner.nextLine();

        //Return the input
        return input;
    }

    /**
     * Prints a message.
     * @param argument  message to be printed
     */
    public void print(String argument){
        System.out.print(argument);
    }

    /**
     * Prints the end message to stdout.
     */
    public void printEnd() {
        // Print the end message
        System.out.println("Clearly you were not worth my time.");
    }

    /**
     * Prints the intro message.
     */
    public void printIntro() {
        // Print the intro messages

        //Logo for the mascot
        String logo = "  _    _ _ _                   \n"
                + " | |  | | | |                  \n"
                + " | |  | | | |_ _ __ ___  _ __  \n"
                + " | |  | | | __| '__/ _ \\| '_ \\ \n"
                + " | |__| | | |_| | | (_) | | | |\n"
                + "  \\____/|_|\\__|_|  \\___/|_| |_|\n";

        //Print the intro
        System.out.println(logo);
        showLine();
        System.out.println("Hello lesser being, I am Ultron\n"
                + "What do you want?\n");
        showLine();
    }

    /**
     * Prints the new line separator.
     */
    public void showLine() {
        System.out.println("_______________________________"
                + "_____________________________\n");
    }
}
