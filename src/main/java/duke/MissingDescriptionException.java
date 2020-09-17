package duke;

/**
 * Creates an exception to be thrown when description of an event or deadline is missing.
 */
public class MissingDescriptionException extends Exception {
    public MissingDescriptionException(String errMessage) {
        super("\n     ☹ OOPS!!! The description of " + errMessage + " cannot be empty.");
    }
}
