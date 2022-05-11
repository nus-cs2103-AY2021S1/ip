/**
 * Exceptions that may occur during users' entering arguments and commands.
 */
public class DukeException extends IllegalArgumentException {

    String dukeMessage;

    /**
     * Produces an exception with a specified message.
     *
     * @param message the message to be sent should this exception is produced
     */
    public DukeException(String message) {
        super(message);
        this.dukeMessage = message;
    }

    /**
     * Produces an exception with a specified message and the cause for it.
     *
     * @param message the message to be sent should this exception is produced
     * @param cause   the cause of the exception
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
        this.dukeMessage = message;
    }

    /**
     * The overridden toString() method that prints out the exception produced.
     *
     * @return the string message as a representation of the error
     */
    @Override
    public String toString() {
        return this.dukeMessage;
    }

}
