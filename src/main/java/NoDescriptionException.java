public class NoDescriptionException extends MissingElementException {
    public NoDescriptionException(String message, String type) {
        super(message + "The description of " + type + " cannot be empty.");
    }
}
