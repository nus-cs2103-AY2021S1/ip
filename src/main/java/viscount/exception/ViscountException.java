package viscount.exception;

/**
 * Represents the exceptions specific to Viscount.
 */
public class ViscountException extends Exception {
    private static final String ERROR_MESSAGE = "Alas, I do apologise but I don't know what that means.";

    /**
     * Instantiates a new ViscountException.
     *
     * @param errorMessage Error message of the exception.
     */
    public ViscountException(String errorMessage) {
        super(errorMessage);
    }
}
