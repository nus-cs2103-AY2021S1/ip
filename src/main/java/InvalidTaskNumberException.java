public class InvalidTaskNumberException extends InvalidInputException {
    InvalidTaskNumberException() {
        super("The task number is invalid!");
    }

    InvalidTaskNumberException(String message) {
        super(message);
    }
}
