package duke.Exception;

/**
 * Represents a custom exception when task description is not given.
 * @author Tee Kok Siang
 */
public class EmptyTaskDescriptionException extends DukeException{
    public EmptyTaskDescriptionException() {
        super(":( Oops!!! The description of a Task cannot be empty. :-(");
    }
}
