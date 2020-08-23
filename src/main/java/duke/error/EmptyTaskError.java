package duke.error;

public class EmptyTaskError extends DukeError {
    public EmptyTaskError() {
        super("Please insert a task!");
    }
}
