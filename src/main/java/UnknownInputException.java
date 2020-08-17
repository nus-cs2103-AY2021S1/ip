public class UnknownInputException extends InvalidInputException {
    UnknownInputException(String message) {
        super(message);
    }

    UnknownInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
