package duke;

/**
 * Handles exceptions specific to Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Prompts the user to enter a command again.
     */
    public static void tryAgain() {
        System.out.println();
        System.out.println("Please try again: ");
    }
}
