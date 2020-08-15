public class EmptyTaskException extends InvalidTaskException {
    protected EmptyTaskException(String type) {
        super("\u2639 OOPS!!! The description of a " + type + " cannot be empty.");
    }
}
