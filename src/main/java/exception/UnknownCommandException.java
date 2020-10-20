package exception;

public class UnknownCommandException extends Exception {

    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
