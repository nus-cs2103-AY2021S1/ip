package duke.Exception;

/**
 * Represents a custom exception when the command does not contain task number.
 * @author Tee Kok Siang
 */
public class InvalidTaskNumberCommandException extends DukeException {
    public InvalidTaskNumberCommandException() {
        super(":( Oops!!! Please type \"[action] [task number]\"");
    }
}
