package exception;

public class ViewallInvalidUsageException extends InvalidUsageException {
    public ViewallInvalidUsageException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: Viewall <date>";
    }
}
