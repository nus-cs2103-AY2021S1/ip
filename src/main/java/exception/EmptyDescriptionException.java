package exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String s) {
        super(String.format("Description of %s is empty, please try again.",s));
    }
}
