package duke;

/**
 * For exceptions that happen while running the Duke.
 */
public class DukeException extends Exception {

    /**
     * DukeException constructor.
     *
     * @param message exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
