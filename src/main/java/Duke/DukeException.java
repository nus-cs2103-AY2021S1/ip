package duke;

/**
 * Represents an exception which may occur during the execution.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException instance.
     * @param s is the error information.
     */
    public DukeException(String s) {
        super(s);
    }
}
