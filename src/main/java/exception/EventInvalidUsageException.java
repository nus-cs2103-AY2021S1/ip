package exception;

public class EventInvalidUsageException extends InvalidUsageException {
    public EventInvalidUsageException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: event <event description> /at <event date>";
    }
}
