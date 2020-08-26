package duke.exception;

public class TaskDoesNotExistException extends IndexOutOfBoundsException {
    public TaskDoesNotExistException(int index) {
        super("OOPS! Task " + index + " does not exist." + "\n" + "Please make sure task index is correct.");
    }
}
