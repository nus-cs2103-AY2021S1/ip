package duke.exception;

/**
 * Represents an exception that the parameter for done command is empty.
 */
public class DoneIndexEmptyException extends DukeException {

    public DoneIndexEmptyException() {
        super("Your index for done command should not be empty");
    }

}
