package duke.exception;

/**
 * Represents a custom exception when the {@link duke.command.FindCommand} is invalid.
 * @author Tee Kok Siang
 */
public class InvalidPriorityCommandException extends DukeException {
    /**
     * Constructs a InvalidPriorityCommandException.
     */
    public InvalidPriorityCommandException() {
        super(":( Oops!!! Please type \"priority [task number] [1: low, 2: medium, 3: high]\" to mark task's priority");
    }
}
