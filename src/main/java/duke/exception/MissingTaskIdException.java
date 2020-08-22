package duke.exception;

public class MissingTaskIdException extends DukeException {
    public MissingTaskIdException(String message) {
        super(message + " No task was specified!");
    }
}
