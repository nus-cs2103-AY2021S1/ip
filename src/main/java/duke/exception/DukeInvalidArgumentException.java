package duke.exception;

/**
 * Represents exception signifying an invalid argument for command.
 */
public class DukeInvalidArgumentException extends DukeException {

    /**
     * Class constructor.
     * @param command String of command to be executed.
     */
    public DukeInvalidArgumentException(String command) {
        super("Please enter a valid argument for " + command + "!");
    }
}
