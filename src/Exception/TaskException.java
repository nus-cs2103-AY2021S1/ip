package Exception;

/**
 * Represents the exception thrown if there are no description of a todo.
 */
public class TaskException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! The description of a todo cannot be empty.\n";
        return s;
    }
}
