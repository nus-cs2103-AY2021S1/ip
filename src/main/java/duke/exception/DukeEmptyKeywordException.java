package duke.exception;

public class DukeEmptyKeywordException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message
     */
    public DukeEmptyKeywordException() {
        super("☹ OOPS!!! Keyword cannot be empty.");
    }
}
