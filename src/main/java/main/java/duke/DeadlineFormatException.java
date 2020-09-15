package main.java.duke;

/**
 * An exception that is thrown when the deadline specified in a task object is of the wrong format.
 */
public class DeadlineFormatException extends DukeException {

    /**
     * Default constructor which creates an instance of DeadlineFormatException with the
     * default error message.
     */
    public DeadlineFormatException() {
        super("  \u2639 OOPS!!! The command for a deadline/event task must follow this format: deadline " +
                "homework /by 2019-11-21 1800");
    }

    /**
     * Creates an instance of DeadlineFormatException, a custom exception
     * for user inputs of the wrong deadline format.
     *
     * @param error the string representation of the error
     */
    public DeadlineFormatException(String error) {
        super(error);
    }
}
