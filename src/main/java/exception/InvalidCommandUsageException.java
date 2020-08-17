package exception;

public class InvalidCommandUsageException extends Exception {
    public InvalidCommandUsageException(String message) {
        super(message);
    }
}
