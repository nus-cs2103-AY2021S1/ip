package duke.exception;

/**
 * Represents a custom exception when the command is invalid.
 *
 * @author Tee Kok Siang
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs a InvalidCommandException.
     */
    public InvalidCommandException() {
        super(":( Oops!!! I'm sorry, but I don't understand. Command:\n\tbye | " +
                "deadline | delete | done | event | find | list | priority | todo ");
    }
}
