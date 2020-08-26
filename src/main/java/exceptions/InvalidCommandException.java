package exceptions;

/**
 * Class to initiate InvalidCommandException.
 * Thrown when input command does not match any of the existing commands.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("☹ OOPS!!! Sorry I do not understand this command!");
    }
}
