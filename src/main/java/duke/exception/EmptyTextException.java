package duke.exception;

public class EmptyTextException extends DukeException {
    public EmptyTextException(String text) {
        super(String.format(" ☹ OOPS! You must fill in the text for %s", text));
    }
}
