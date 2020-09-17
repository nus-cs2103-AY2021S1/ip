package duke.exception;

/**
 * Exception that occurs when the user does not specify the date the Event.
 */
public class EventMissingDateException extends InvalidInputException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Please specify the date of the Event.";
    }
}