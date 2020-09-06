package duke;

/**
 * Represents exception signifying an unknown command.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Class constructor.
     */
    public DukeUnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means. Please enter your task " +
                "with the starting keyword \"todo\" or \"deadline\" or \"event\".");
    }
}
