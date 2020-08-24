package duke.exceptions;

/** Thrown to indicate that the user input an invalid command. */
public class InvalidCommandException extends DukeException {

    /** Constructs the InvalidCommandException with the relevant detail message. */
    public InvalidCommandException() {
        super("OOPS! I'm sorry but I don't know what that means :-(");
    }
}
