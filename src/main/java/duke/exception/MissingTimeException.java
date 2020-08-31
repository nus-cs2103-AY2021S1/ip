package duke.exception;

public class MissingTimeException extends DukeException {
    public MissingTimeException(String byOrAt) {
        super("Error! '/" + byOrAt + "' date not found.");
    }
}
