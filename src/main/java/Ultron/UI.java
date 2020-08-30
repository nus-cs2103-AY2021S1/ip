package ultron;

import java.util.Scanner;

public final class UI {

    /**
     * Store the scanner object.
     */
    private final Scanner scanner;
    private String string;

    /**
     * UI to handle all the displaying.
     */
    public UI() {

        //Create a new scanner object
        scanner = new Scanner(System.in);
        setIntro();
    }

    public static String getIntro() {

        //Print the intro
        return "Hello lesser being, I am Ultron\n"
            + "What do you want?\n";
    }

    /**
     * Show the error as output to System out.
     *
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
     *
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
     *
     * @param argument message to be printed
     */
    public void print(final String argument) {
        System.out.println(argument);
    }

    /**
     * Get the message that is currently stored
     */
    public String getMessage() {
        return getLine() + string + getLine();
    }

    /**
     * Set the message to be shown in gui.
     */
    public void setMessage(final String argument) {
        string = argument;
    }

    /**
     * Prints the end message to stdout.
     */
    public void printEnd() {
        // Print the end message
        print(getEnd());
    }

    /**
     * Set the end message.
     */
    public void setEnd() {
        setMessage("Clearly you were not worth my time.");
    }

    /**
     * Set the intro message
     */
    public void setIntro() {
        setMessage(getIntro());
    }

    private String getEnd() {
        return "Clearly you were not worth my time.\n";
    }

    /**
     * Prints the intro message.
     */
    public void printIntro() {
        // Print the intro messages
        print(getIntro());
    }

    /**
     * Get the new line separator.
     *
     * @return String newline seperator
     */
    private String getLine() {
        return "";
    }

    /**
     * Prints the new line separator.
     */
    public void showLine() {
        print(getLine());
    }
}
