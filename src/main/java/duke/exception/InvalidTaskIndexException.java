package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions require a list index but the index is invalid.
 */
public class InvalidTaskIndexException extends DukeException {

    public InvalidTaskIndexException() {
        super("Task Number is invalid! Please check!", InvalidTaskIndexException.class.getName());
    }
}
