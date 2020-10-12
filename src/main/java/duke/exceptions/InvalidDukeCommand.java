package duke.exceptions;

/**
 * Represents an Invalid Duke command exception.
 */
public class InvalidDukeCommand extends DukeException {

    /**
     * Message shown to the user when they enter an invalid command.
     *
     * @return Error message string.
     */
    @Override
    public String toString() {
        return super.toString() + " I'm sorry, but I don't know what that means :-(";
    }
}
