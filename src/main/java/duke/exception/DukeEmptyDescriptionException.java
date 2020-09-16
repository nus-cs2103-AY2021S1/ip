package duke.exception;

/**
 * Represents exception signifying empty description for command.
 */
public class DukeEmptyDescriptionException extends DukeException {

    /**
     * Class constructor.
     * @param command String of command executed.
     */
    public DukeEmptyDescriptionException(String command) {
        super("Description for " + command + " cannot be empty!");
    }
}
