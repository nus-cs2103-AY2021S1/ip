package duke.exception;

/**
 * Represents an exception when user does not input any LocalDateTime when adding events.
 */
public class NoTaskDurationException extends DukeException{

    /**
     * Constructs a NoTaskDurationException.
     *
     * @param errorMessage Error Message to show.
     */
    public NoTaskDurationException(String errorMessage) {
        super(errorMessage);
    }
}
