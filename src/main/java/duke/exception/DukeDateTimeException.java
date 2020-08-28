package duke.exception;

/**
 * Exception for handling invalid date/time inputs.
 */
public class DukeDateTimeException extends DukeException {
    public DukeDateTimeException(String input) {
        super(input);
    }

    /**
     * Returns the error message containing the user input which caused the error. 
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke doesn't recognise the date/time -> " + input;
    }
}
