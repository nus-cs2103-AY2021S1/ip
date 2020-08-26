package duke.exception;

public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("\uD83D\uDE41 OOPS! I'm sorry, but I don't know what that means :-(");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
