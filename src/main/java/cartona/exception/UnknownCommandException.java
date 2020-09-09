package cartona.exception;

public class UnknownCommandException extends InvalidInputException {
    UnknownCommandException(String errorMsg) {
        super(errorMsg);
    }
}
