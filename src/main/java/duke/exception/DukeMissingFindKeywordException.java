package duke.exception;

/**
 * Represents the exception when the keyword to find is missing.
 */
public class DukeMissingFindKeywordException extends DukeException {

    private static final String ERROR_MESSAGE = "Please indicate the keyword which you want to find.\n";

    public DukeMissingFindKeywordException() {
        super(ERROR_MESSAGE);
    }
}
