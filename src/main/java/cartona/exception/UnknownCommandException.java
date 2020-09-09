package cartona.exception;

public class UnknownCommandException extends InvalidInputException {
    public UnknownCommandException(String errorMsg) {
        super(errorMsg);
    }
}
