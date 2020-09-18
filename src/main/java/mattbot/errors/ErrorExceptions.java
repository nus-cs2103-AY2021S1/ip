package mattbot.errors;

/**
 * Represents the error that will be thrown.
 */
public class ErrorExceptions extends Exception {
    /**
     * Throws an ErrorExceptions object with the input message.
     * @param message message when printing the error.
     */
    public ErrorExceptions(String message) {
        super(message);
    }
}
