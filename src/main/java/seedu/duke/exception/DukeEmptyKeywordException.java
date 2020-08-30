package seedu.duke.exception;

/**
 * Represents an exception thrown when keyword for search is left empty.
 * Keyword is required for searching tasks.
 */
public class DukeEmptyKeywordException extends DukeException {
    /**
     * Class constructor.
     */
    public DukeEmptyKeywordException() {
        super("☹ OOPS!!! Keyword cannot be empty.");
    }
}
