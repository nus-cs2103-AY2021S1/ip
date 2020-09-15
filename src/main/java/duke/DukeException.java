package duke;

/**
 * Represents en exception that occurs due to running Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with error message, prefixed with identifier string.
     * @param errorMessage Error message to be printed out.
     */
    DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
