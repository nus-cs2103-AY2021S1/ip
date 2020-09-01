package dude.util;
/**
 * CommandException class to denote an issue with the arguments following the command.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Constructor for the InvalidArgumentException class.
     * @param message the error message.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
