package duke;

/**
 * Exception class to define Duke-related exceptions and handle them.
 */
public class DukeException extends Exception {

    /**
     * Creates an exception with the given message.
     *
     * @param message Exception message
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("OOPS!!! %s", super.getMessage());
    }
}
