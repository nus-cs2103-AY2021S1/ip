package duke.exception;

/**
 * Exception for handling done or delete commands with an invalid index.
 */
public class DukeOutOfBoundsException extends DukeException {
    public DukeOutOfBoundsException(String input) {
        super(input);
    }

    /**
     * Returns the error message containing the user input which caused the error. 
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke can't find the task in the list -> " + input;
    }
}
