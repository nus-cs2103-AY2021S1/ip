public class UnknownInputException extends InvalidInputException {
    public UnknownInputException(String message) {
        super(message);
    }

    public UnknownInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
