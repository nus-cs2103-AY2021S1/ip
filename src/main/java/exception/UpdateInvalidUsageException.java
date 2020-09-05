package exception;

public class UpdateInvalidUsageException extends InvalidUsageException {
    public UpdateInvalidUsageException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: Update <tasknumber> [/t <date>] [/d <description>]";
    }
}
