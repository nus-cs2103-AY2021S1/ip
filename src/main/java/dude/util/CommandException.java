package dude.util;

/**
 * CommandException class to denote an issue with the command given by the user.
 */
public class CommandException extends Exception {
    /**
     * Constructor for the CommandException class.
     * @param message the error message.
     */
    public CommandException(String message) {
        super(message);
    }
}
