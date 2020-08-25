package Duke.Exceptions;

/**
 * The exception deals with the no description when formatting a task.
 */
public class NoDescriptionException extends DukeException{
    public NoDescriptionException(String task) {
        super(String.format("  ☹ OOPS!!! The description of a %s cannot be empty.", task));
    }
}
