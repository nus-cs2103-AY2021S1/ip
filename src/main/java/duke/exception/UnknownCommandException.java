package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
