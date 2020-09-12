package clippy.exception;

public class InvalidCommandException extends ClippyException {
    public InvalidCommandException() {
        super("Oops! I'm sorry, but I don't know what that means. :(");
    }
}
