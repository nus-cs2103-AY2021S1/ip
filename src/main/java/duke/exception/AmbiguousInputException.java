package duke.exception;

/**
 * Represents an exception when the system cannot recognize the command (input).
 */
public class AmbiguousInputException extends DukeException{

    public AmbiguousInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
