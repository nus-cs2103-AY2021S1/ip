package duke.exception;

public class MissingTaskException extends DukeException {
    public MissingTaskException() {
        super("Error! No task description provided.");
    }
}
