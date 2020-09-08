package duke.Exception;

/**
 * Represents a custom exception when the {@link duke.command.AddCommand} for Deadline is invalid.
 * @author Tee Kok Siang
 */
public class InvalidDeadlineCommandException extends DukeException {
    public InvalidDeadlineCommandException() {
        super(":( Oops!!! Please type \"deadline [task description] /by [yyyy-mm-dd]\" "
                + "to add a Deadline task");
    }
}
