package duke.exception;

/**
 * Exception that occurs when the user does not input anything (only hit enter).
 */
public class NoInputException extends DukeException{

    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " No input detected.";
    }
}
