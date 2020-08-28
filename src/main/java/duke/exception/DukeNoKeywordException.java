package duke.exception;

/**
 * Exception for handling find commands with no keyword.
 */
public class DukeNoKeywordException extends DukeException {
    public DukeNoKeywordException(String input) {
        super(input);
    }

    /**
     * Returns the error message with the input that causes the error.
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke doesn't know what to find! -> " + input;
    }
}
