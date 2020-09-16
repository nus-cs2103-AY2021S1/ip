package duke.exception;

/**
 * Represents exception signifying an invalid command.
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Class constructor.
     */
    public DukeInvalidCommandException() {
        super("Invalid command!");
    }
}
