package exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException (String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
