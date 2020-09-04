package seedu.duke;

import java.util.Scanner;

/**
 * Represents a group of methods that deal with interactions with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "      ===================================";
    private static final String INDENTATION = "      ";
    private Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Takes in one line of input from the user.
     *
     * @return the user's input
     * @throws DukeException if there is no more input
     */
    String readCommand() throws DukeException {
        if (this.scanner.hasNext()) {
            return scanner.nextLine().trim();
        } else {
            throw new DukeException("No next line");
        }
    }

    /**
     * Prints out an message to the user.
     *
     * @param message the message to be printed
     */
    public void showMessage(Message message) {
        System.out.println(HORIZONTAL_LINE);
        message.forEach(line -> System.out.println(INDENTATION + line));
        System.out.println(HORIZONTAL_LINE);
    }
}
