package duke.parser;

/**
 * This exception is handled within the route function in the Duke class.
 */
class DukeParsingException extends Exception {
    DukeParsingException(String message) {
        super(message);
    }
}
