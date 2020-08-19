public class TaskDoesNotExistException extends IndexOutOfBoundsException {
    TaskDoesNotExistException(String message) {
        super(message);
    }
}
