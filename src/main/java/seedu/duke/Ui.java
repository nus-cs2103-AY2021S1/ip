package seedu.duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class that represents the interface of Duke.
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a line separator.
     */
    public void linePrinter() {
        System.out.println("\n-------------------------------------------------------------------------\n");
    }

    /**
     * Prints the starting introduction of Duke.
     */
    public void start() {
        linePrinter();
        printResult("Hello! I'm Duke\n" +
                "What can I do for you?");
        linePrinter();
    }

    /**
     * Prints an error message when the text file does not load properly.
     */
    public void showLoadingError() {
        System.out.println("Whoops! Some kind of error :/ see here: ");
    }

    /**
     * Prints a goodbye message when Duke is shut down.
     */
    public void bye() {
        String byeText = "Running away huh??";
        printResult(byeText);
        linePrinter();
    }

    /**
     * Gets the next line of the user input.
     * @return String that contains the next line of the user input.
     */
    public String getUserCommand() {
        return this.in.nextLine();
    }

    /**
     * Prints out any errors.
     * @param error String representing the error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints out any results from the user commands.
     * @param lines String representing the output from Duke.
     */
    public void printResult(String lines) {
        System.out.println(lines.replaceAll("(?m)^", "\t"));
    }
}
