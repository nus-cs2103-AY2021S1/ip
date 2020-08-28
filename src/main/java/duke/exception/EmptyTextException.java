package duke.exception;

public class EmptyTextException extends DukeException {
    private static final String EMPTY_EXCEPTION = " ☹ OOPS! You must fill in the text for %s";
    public EmptyTextException(String text) {
        super(String.format(EMPTY_EXCEPTION, text));
    }
}
