package duke.exception;

/**
 * Exception for handling find commands with too many keywords.
 */
public class DukeTooManyKeywordsException extends DukeException {
    public DukeTooManyKeywordsException(String input) {
        super(input);
    }

    /**
     * Returns the error message with the input that causes the error.
     * 
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke can only handle so many keyword(s)! -> " + input;
    }
}
