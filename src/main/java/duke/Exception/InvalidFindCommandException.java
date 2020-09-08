package duke.Exception;

/**
 * Represents a custom exception when the {@link duke.command.FindCommand} is invalid.
 * @author Tee Kok Siang
 */
public class InvalidFindCommandException extends DukeException {
    public InvalidFindCommandException() {
        super(":( Oops!!! Please type \"find [keyword]\" to find a task");
    }
}
