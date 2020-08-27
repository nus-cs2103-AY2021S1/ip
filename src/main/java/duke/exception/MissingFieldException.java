package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions that have one or more fields missing.
 */
public class MissingFieldException extends DukeException {
    public MissingFieldException() {
        super("One or more of the required fields are missing!", MissingFieldException.class.getName());
    }
}
