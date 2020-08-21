package exception;

public class UnknownCommandException extends Exception {
    @Override
    public String getMessage() {
        return "     I'm sorry, but I don't know what that means :-(";
    }
}
