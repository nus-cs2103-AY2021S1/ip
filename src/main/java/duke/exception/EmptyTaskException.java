package duke.exception;

public class EmptyTaskException extends DukeException {
    private static final String MESSAGE = "QUACK!!! The description of a task cannot be empty.";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
