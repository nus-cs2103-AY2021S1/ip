package duke.exception;

/**
 * Exception representing general invalid input.
 */
public class InvalidInputException extends DukeException {

    public InvalidInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
