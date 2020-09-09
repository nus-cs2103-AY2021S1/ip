package duke.exception;

/**
 * Represents a custom exception when the {@link duke.command.AddCommand} for Deadline is invalid.
 * @author Tee Kok Siang
 */
public class InvalidDeadlineCommandException extends DukeException {
    /**
     * Constructs a InvalidDeadlineCommandException.
     */
    public InvalidDeadlineCommandException() {
        super(":( Oops!!! Please type \"deadline [task description] /by [yyyy-mm-dd]\" "
                + "to add a Deadline task");
    }
}
