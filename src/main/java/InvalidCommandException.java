public class InvalidCommandException extends Throwable {
    protected String errorMessage;

    public InvalidCommandException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
