package duke.exception;

/**
 * Represents exception signifying empty argument for command.
 */
public class DukeEmptyArgumentException extends DukeException {

    /**
     * Class constructor.
     * @param command String of command executed.
     */
    public DukeEmptyArgumentException(String command) {
        super("Argument for " + command + " cannot be empty!");
    }
}
