public class EmptyTaskException extends MissingElementException {
    public EmptyTaskException() {
        super("A task cannot be empty.");
    }
}
