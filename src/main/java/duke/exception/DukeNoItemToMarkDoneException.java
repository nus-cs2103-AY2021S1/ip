package duke.exception;

/**
 * Exception for handling done commands with no specified index.
 */
public class DukeNoItemToMarkDoneException extends DukeException {
    public DukeNoItemToMarkDoneException(String input) {
        super(input);
    }

    /**
     * Returns the error message containing the user input which caused the error. 
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke doesn't know what to mark as done -> " + input;
    }
}
