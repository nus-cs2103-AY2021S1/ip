package duke.exception;

public class DuplicateException extends DukeException {

    public DuplicateException(String task) {
        super(String.format("Task '%s' already exist in the list", task));
    }

    public String toString() {
        return getMessage();
    }
}
