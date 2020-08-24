package duke.exceptions;

/**
 * Exception class to represent exception for invalid commands with no descriptions.
 */
public class EmptyDukeException extends DukeException {

    /**
     * Class constructor.
     *
     * @param message
     */

    public EmptyDukeException(String message) {
        super(message);
    }
}