package duke;

public class MocoException extends Exception {
    /**
     * Constructor to throw exception for Moco
     * If expected error is met, Exception is thrown and
     * user is prompted.
     *
     * @param errorMessage message for error that occurred.
     */
    public MocoException(String errorMessage) {
        super(errorMessage);
    }
}
