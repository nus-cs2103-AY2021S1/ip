package duke;

/**
 * Represents exception signifying an empty description.
 */
public class DukeEmptyDescriptionException extends DukeException {

    /**
     * Class constructor.
     * @param command String of command to be executed.
     */
    public DukeEmptyDescriptionException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
