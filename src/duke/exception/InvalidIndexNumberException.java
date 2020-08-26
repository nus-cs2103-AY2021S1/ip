package duke.exception;

public class InvalidIndexNumberException extends NumberFormatException {
    public InvalidIndexNumberException() {
        super("OOPS! Please enter a numerical number to mark tasks as done :)");
    }
}
