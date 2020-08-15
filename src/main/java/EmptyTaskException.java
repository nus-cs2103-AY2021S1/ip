public class EmptyTaskException extends InvalidTaskException {
    public EmptyTaskException(String type) {
        super("\u2639 OOPS!!! The description of a " + type + " cannot be empty.");
    }
}
