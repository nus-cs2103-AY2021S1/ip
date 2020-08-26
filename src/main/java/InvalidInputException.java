public class InvalidInputException extends Throwable {
    protected String errorMessage;

    public InvalidInputException(String message) {
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
