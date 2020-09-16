package exception;

/**
 * Inherits from the DukeException class and is thrown
 * when the command "done" or "delete" is followed
 * by a task number that does not exist, or when the
 * priority level indicated is negative.
 */
public class NumberOutOfRangeException extends DukeException {

    /**
     * Creates a NumberOutOfRangeException object.
     */
    public NumberOutOfRangeException() {
        super("Invalid task number.");
    }
}
