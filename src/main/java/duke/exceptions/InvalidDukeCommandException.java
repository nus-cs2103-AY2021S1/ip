package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not input a valid Duke command. */
public class InvalidDukeCommandException extends DukeException {

    /** Constructor. */
    public InvalidDukeCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
