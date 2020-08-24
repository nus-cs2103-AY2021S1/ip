package exception;

public class InvalidUsageException extends Exception {
    public InvalidUsageException(String message) {
        super("     " + message);
    }

    @Override
    public String getMessage() {
        return "Invalid usage!\n" + super.getMessage();
    }
}
