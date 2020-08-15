public class EmptyTimeException extends InvalidTaskException {
    public EmptyTimeException(String type) {
        super("The time specification for " + type + " cannot be empty.");
    }
}
