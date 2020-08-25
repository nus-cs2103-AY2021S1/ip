package Duke.Exceptions;

/**
 * The class deals with no selecting a task when trying to
 * done or delete a task.
 */
public class NoTaskChosenException extends DukeException{
    public NoTaskChosenException(String task) {
        super(String.format("  ☹ OOPS!!! The task of a %s operation cannot be empty.", task));
    }
}
