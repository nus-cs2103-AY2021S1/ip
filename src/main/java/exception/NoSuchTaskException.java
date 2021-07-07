package exception;

/**
 * Represents an exception stating the task is not within the list.
 */
public class NoSuchTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The index provided is not within the task list";
    }
}
