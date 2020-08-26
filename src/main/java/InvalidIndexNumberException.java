public class InvalidIndexNumberException extends NumberFormatException {
    InvalidIndexNumberException() {
        super("OOPS! Please enter a numerical number to mark tasks as done :)");
    }
}
