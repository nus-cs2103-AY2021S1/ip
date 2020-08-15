public class InvalidTaskException extends InvalidCommandException {
    public InvalidTaskException() {
        super();
    }

    protected InvalidTaskException(String msg) {
        super(msg);
    }
}
