package nite.exception;

/**
 * Represents NiteExceptions used in Nite-bot.
 */
public class NiteException extends Exception {

    /**
     * Creates a NiteException with a specified message.
     *
     * @param errorMessage Error message to be shown.
     */
    public NiteException(String errorMessage) {
        super(errorMessage);
    }

}
