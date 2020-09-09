package duke.exception;

public class MissingTaskIndexException extends DukeException {
    public MissingTaskIndexException() {
        super("Error! No task index provided.");
    }
}
