package duke.exception;

/**
 * Encapsulates the exception when some keyword is expected, but none was found.
 */
public class MissingKeywordException extends DukeException {
    /**
     * Initialises a new instance with the specified detail message followed by a warning to
     * specify a keyword.
     *
     * @param message The detail message.
     */
    public MissingKeywordException(String message) {
        super(String.format("%s Please specify a keyword!", message));
    }
}
