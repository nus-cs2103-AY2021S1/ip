public class NoTimeException extends MissingElementException {
    public NoTimeException(String message, TaskType type) {
        super(message + "The time of a " + type.toString().toLowerCase() + " cannot be empty.");
    }
}
