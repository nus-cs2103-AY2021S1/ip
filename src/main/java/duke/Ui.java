package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class responsible for the look
 * and feel of the console messages.
 */
public class Ui {

    private final Scanner sc;
    private final static String ERRORBORDER = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    private final static String BORDER = "===================================================";
    private final static String INDENT = "    ";

    /**
     * Initializes the Ui object with
     * a Scanner object that scans from
     * System.in.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets user input.
     * @return The user input.
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints the greeting message
     * when Duke is first launched.
     */
    public void printGreeting() {
        ArrayList<String> welcomeTextBlock = new ArrayList<>(List.of(
                "Hello, my name is ",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "How may I help you?"
        ));

        printWithWrapper(welcomeTextBlock, false, false);
    }

    /**
     * Prints the exit message
     * when the user exits Duke.
     */
    public void printExit() {
        printWithWrapper(new ArrayList<>(List.of("Bye bye! Hope to see you again soon!")), false, false);
    }

    /**
     * Wraps the console message with borders and
     * indentation for better UI.
     * @param toPrint The List of messages to be printed to
     *                the console.
     * @param withNumbering Indicates whether the messages
     *                      should be numbered when printing.
     * @param isError Indicates whether the messages are
     *                error messages that requires a different UI.
     * @param <T> The generic type of the messages to be printed.
     */
    public <T> void printWithWrapper(ArrayList<T> toPrint, boolean withNumbering, boolean isError) {
        printBorder(isError);
        for (int i = 0; i < toPrint.size(); i++) {
            if (toPrint.get(i) == null) {
                break;
            }

            String prepend = INDENT;
            if (withNumbering) {
                prepend += (i + 1) + ". ";
            }
            System.out.println(prepend + toPrint.get(i).toString());
        }
        printBorder(isError);
    }

    private void printBorder(boolean isError) {
        System.out.println((isError ? ERRORBORDER : BORDER));
    }
}
