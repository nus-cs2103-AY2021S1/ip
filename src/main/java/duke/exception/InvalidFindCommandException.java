package duke.exception;

/**
 * Represents a custom exception when the {@link duke.command.FindCommand} is invalid.
 * @author Tee Kok Siang
 */
public class InvalidFindCommandException extends DukeException {
    /**
     * Constructs a InvalidFindCommandException.
     */
    public InvalidFindCommandException() {
        super(":( Oops!!! Please type \"find [keyword]\" to find a task");
    }
}
