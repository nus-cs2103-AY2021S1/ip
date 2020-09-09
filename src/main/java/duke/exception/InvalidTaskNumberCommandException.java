package duke.exception;

/**
 * Represents a custom exception when the command does not contain task number.
 *
 * @author Tee Kok Siang
 */
public class InvalidTaskNumberCommandException extends DukeException {
    /**
     * Constructs a InvalidTaskNumberCommandException.
     */
    public InvalidTaskNumberCommandException() {
        super(":( Oops!!! Please type \"[action] [task number] [optional arguments...]\"");
    }
}
