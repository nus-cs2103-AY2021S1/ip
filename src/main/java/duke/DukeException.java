package duke;

/**
 * Represents Duke Exception class.
 */
public class DukeException extends Exception {

    /** Error message of exception */
    private String message;

    /**
     * Constructs an exception specific to Duke program.
     *
     * @param message Error message of exception.
     */
    public DukeException(String message) {
        super("OOPS!!! :( " + message);
    }
}
