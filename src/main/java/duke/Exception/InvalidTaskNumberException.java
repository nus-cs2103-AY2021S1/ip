package duke.Exception;

/**
 * Represents a custom exception when the task number is invalid.
 * @author Tee Kok Siang
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super(":( Oops!!! The task number is invalid. :-(");
    }
}
