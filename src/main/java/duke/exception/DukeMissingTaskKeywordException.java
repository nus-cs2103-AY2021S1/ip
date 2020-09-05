package duke.exception;

/**
 * Represents the exception when the keyword for deadline or event tasks are missing.
 */
public class DukeMissingTaskKeywordException extends DukeException {

    private static final String ERROR_MESSAGE = "Please indicate a date using the %s keyword.\n";

    public DukeMissingTaskKeywordException(String keyword) {
        super(String.format(ERROR_MESSAGE, keyword));
    }
}
