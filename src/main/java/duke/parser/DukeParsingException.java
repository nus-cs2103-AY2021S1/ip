package duke.parser;

/**
 * This exception is handled within the route function in the Duke class.
 */
public class DukeParsingException extends Exception {
    public DukeParsingException(String message) {
        super(message);
    }
}
