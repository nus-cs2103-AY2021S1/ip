package duke;

/**
 * Represents Duke Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructs an exception specific to Duke program.
     * @param errorMessage Error message of exception.
     */
    public DukeException(String errorMessage) {
        super("OOPS!!! :( " + errorMessage);
    }
}
