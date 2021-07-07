package seedu.duke.ui;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class that represents the interface of Duke.
 */
public class Ui {

    private final Scanner in;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Prints the starting introduction of Duke.
     */
    public void start() {
        printResult("Hey I'm Duke...\n"
                + "What do you wanna do?\n"
                + "I ain't got all day.");
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
        System.out.println(lines);
    }

}
