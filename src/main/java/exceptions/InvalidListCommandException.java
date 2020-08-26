package exceptions;

/**
 * Class to initiate InvalidListCommandException.
 * Thrown when there are extra input after the list command.
 */
public class InvalidListCommandException extends DukeException{
    public InvalidListCommandException() {
        super("list command is invalid! Input \"list\" to show task in the task list.");
    }
}
