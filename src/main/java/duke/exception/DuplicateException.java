package duke.exception;

public class DuplicateException extends DukeException {
    private static final String DUPLICATE_EXCEPTION = " ☹ OOPS! This specific task exist before!";
    public DuplicateException() {
        super(DUPLICATE_EXCEPTION);
    }
}
