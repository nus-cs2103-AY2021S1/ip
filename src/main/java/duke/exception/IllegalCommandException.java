package duke.exception;

/**
 * The exception to be thrown when Duke encounters non-understandable commands.
 */
public class IllegalCommandException extends Exception {
    /**
     * Construct a new exception with the command that causes the confusion.
     * @param command the command that confuses Duke
     */
    public IllegalCommandException(String command) {
        super("☹ OOPS!!! I cannot recognise the command \"" + command + "\" :-(");
    }
}
