package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not input a valid Duke command. */
public class InvalidDukeCommandException extends DukeException {

    /** Constructs a new InvalidDukeCommandException object with the specified error message. */
    public InvalidDukeCommandException() {
        super("Harh? I don't know what that means.");
    }
}
