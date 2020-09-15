package main.java.duke;

/**
 * An exception that is thrown when the description for a task is invalid.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Default constructor which creates an instance of EmptyDescriptionException with the
     * default error message.
     */
    public EmptyDescriptionException() {
        super("  \u2639 OOPS!!! The " +
                "description" +
                " of a task cannot be empty.");
    }

    /**
     * Creates an instance of EmptyDescriptionException, a custom exceptions for user commands with no task
     * description
     *
     * @param error the string representation of the error
     */
    public EmptyDescriptionException(String error) {
        super(error);
    }
}
