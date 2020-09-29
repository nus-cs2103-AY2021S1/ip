package duke;

import java.util.Scanner;

/**
 * Represents a group of methods that deal with interactions with the user.
 */
public class Ui {
    protected static final String HORIZONTAL_LINE = "      ===================================";
    protected static final String INDENTATION = "      ";
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
     */
    String readCommand() {
        assert this.scanner.hasNext();
        return scanner.nextLine().trim();
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
