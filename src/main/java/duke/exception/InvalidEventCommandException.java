package duke.exception;

/**
 * Represents a custom exception when the {@link duke.command.AddCommand} for Event is invalid.
 * @author Tee Kok Siang
 */
public class InvalidEventCommandException extends DukeException {
    /**
     * Constructs a InvalidEventCommandException.
     */
    public InvalidEventCommandException() {
        super(":( Oops!!! Please type \"event [task description] /at [event time] \" "
                + "to add a Event task");
    }
}
