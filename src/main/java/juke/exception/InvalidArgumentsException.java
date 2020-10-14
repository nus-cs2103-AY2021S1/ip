package juke.exception;

public class InvalidArgumentsException extends DukeException {

    /**
     * Constructs a DukeException with a given message.
     *
     * @param message Error Message to be output to the user.
     */
    public InvalidArgumentsException(String message) {
        super(message);
    }
}
