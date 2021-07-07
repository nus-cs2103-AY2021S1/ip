package exception;

public class DeadlineInvalidUsageException extends InvalidUsageException {
    public DeadlineInvalidUsageException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: deadline <deadline description> /by <deadline date>";
    }
}
