/**
 * This is the InvalidInputException class that is thrown when an invalid is given.
 */
public class InvalidInputException extends Throwable {
    protected String errorMessage;

    public InvalidInputException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
