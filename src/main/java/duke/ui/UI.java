package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates the UI.
 *
 * Wraps around a Scanner for input and System.out for printing.
 */
public class UI {
    private static final String DIVIDER = "-----------------------------------------------------";
    private static final String HELLO_MESSAGE = "Greetings! I am Duke.\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! See you around!";
    private static final String ERROR_MESSAGE = "Hmm I didn't quite catch that.";

    private final Scanner scanner;

    /**
     * Constructs the UI object.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the a divider line that divides input and output.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Shows a hello message.
     */
    public void showHelloMessage() {
        showLine();
        System.out.println(HELLO_MESSAGE);
        showLine();
    }

    /**
     * Shows an exit message.
     */
    public void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Shows the error message with text.
     *
     * @param message Message from the Exception.
     */
    public void showErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE + "\n" + message);
    }

    /**
     * Reads the next line in user's input.
     *
     * @return The full user's input.
     * @throws UIException If user's input is empty or the scanner has no next line.
     */
    public String readCommand() throws UIException {
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.equals("")) {
                return line;
            } else {
                throw new UIException("Input cannot be empty.");
            }
        } else {
            throw new UIException("Invalid call to readCommand");
        }
    }

    /**
     * Shows a message.
     *
     * @param message Message to output.
     */
    public void printResult(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }
}
