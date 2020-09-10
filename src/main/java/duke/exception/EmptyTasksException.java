package duke.exception;

public class EmptyTasksException extends DukeException {

    public EmptyTasksException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Cannot operate on empty tasks list!";
    }
}
