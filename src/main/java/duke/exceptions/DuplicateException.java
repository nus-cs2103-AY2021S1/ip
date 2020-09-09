package duke.exceptions;

public class DuplicateException extends DukeException {
    public DuplicateException() {
        super("There appears to be a duplicate item");
    }
}
