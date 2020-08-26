package duke.exception;

/**
 * An indication of parsing errors due to invalid
 * date/time string format.
 */
public class DukeParseException extends DukeException{

    public DukeParseException(String message) {
        super(message);
    }
}