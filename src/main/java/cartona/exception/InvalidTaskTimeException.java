package cartona.exception;

public class InvalidTaskTimeException extends InvalidInputException {
    InvalidTaskTimeException(String errorMsg) {
        super(errorMsg);
    }
}
