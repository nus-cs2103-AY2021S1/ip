package duke.exceptions;

public class DukeInvalidCommandException extends DukeException {
    /**
     * Class constructor.
     *
     */
    public DukeInvalidCommandException() {
        super("Sorry, but your command is invalid. Do check the UG for a command list!");
    }
}
