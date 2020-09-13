package duke;

/**
 * Exception thrown when program encounters errors.
 */
public class DukeException extends Exception {

    /**
     * Exception duke throws whenever there are errors encountered.
     *
     * @param message the specific details of the error thrown.
     */
    public DukeException(String message) {
        super("Hi my friend, " + message);
    }
}
