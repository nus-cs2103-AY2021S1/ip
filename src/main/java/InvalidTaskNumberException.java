public class InvalidTaskNumberException extends InvalidInputException {
    InvalidTaskNumberException() {
        super("The task to be marked as done does not exist!");
    }

    InvalidTaskNumberException(String message) {
        super(message);
    }
}
