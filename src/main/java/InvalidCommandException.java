public class InvalidCommandException extends Throwable {
    protected String errorMessage;

    public InvalidCommandException(String message) {
        this.errorMessage = message;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
