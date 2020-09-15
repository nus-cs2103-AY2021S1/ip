package duke.util;

/**
 * Duke exception encompasses all the possible native exceptions generated
 * in the program by wrapping them under the context of the chat bot application.
 * Personality of the bot can be easily portrayed in the standardized prefix of the
 * error messages.
 */
public class DukeException extends Exception {
    /**
     * Constructs the duke exception.
     * @param message the error message.
     */
    public DukeException(String message) {
        super(">< " + message);
    }
}
