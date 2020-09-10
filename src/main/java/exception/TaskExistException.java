package exception;

/**
 * Represents the exception thrown if there already exists such a task.
 */
public class TaskExistException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! The task already exists in the list.\n";
        return s;
    }
}
