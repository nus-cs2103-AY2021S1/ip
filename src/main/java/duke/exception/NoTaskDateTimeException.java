package duke.exception;

/**
 * Represents an exception when user does not input any LocalDateTime when adding Deadline.
 */
public class NoTaskDateTimeException extends DukeException {

    /**
     * Constructs a NoTaskDateTimeException.
     *
     * @param errorMessage Error Message to show.
     */
    public NoTaskDateTimeException(String errorMessage) {
        super(errorMessage);
    }
}
