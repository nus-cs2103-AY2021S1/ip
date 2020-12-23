/**
 * This is the InvalidCommandException class that is thrown when a command is given,
 * but the command in the wrong form.
 */
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
