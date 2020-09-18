package cartona.exception;

public class EmptyTaskDescriptionException extends InvalidInputException {
    public EmptyTaskDescriptionException(String errorMsg) {
        super(errorMsg);
    }
}
