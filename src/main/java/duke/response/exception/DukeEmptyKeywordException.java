package duke.response.exception;

/**
 * Represents an exception thrown when keyword for search is left empty.
 * Keyword is required for searching tasks.
 */
public class DukeEmptyKeywordException extends DukeInputException {
    /**
     * Class constructor.
     */
    public DukeEmptyKeywordException() {
        super("OOPS!!! Keyword cannot be empty.");
    }
}
