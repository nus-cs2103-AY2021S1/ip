/**
 * Represents a general Exception class of Exceptions that occur while DukeException is running.
 */
public class DukeException extends RuntimeException {

    /**
     * Creates a DukeException object with a customised error description.
     * @param description description of the type of error encountered
     */
    public DukeException(String description) {
        super("☹ OOPS!!! " + description);
    }

}
