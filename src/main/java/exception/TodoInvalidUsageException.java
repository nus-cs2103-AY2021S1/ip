package exception;

public class TodoInvalidUsageException extends InvalidUsageException {
    public TodoInvalidUsageException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: todo <todo description>";
    }
}
