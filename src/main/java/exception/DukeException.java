package exception;

public class DukeException extends Exception {
    public DukeException (String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Invalid use of Duke: " + super.getMessage();
    }
}
