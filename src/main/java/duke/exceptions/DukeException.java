package duke.exceptions;

/**
 * Represents errors that occurs as a result of running the program.
 */
public class DukeException extends Exception {
    /**
     * Instantiates a DukeException object.
     *
     * @param msg The specific error message.
     */
    public DukeException(String msg) {
        super("UHOH! " + msg);
    }
}
