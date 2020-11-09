package duke.designpattern.command;

/*
Javadoc comments copied/adapted from
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/RuntimeException.html
 */

/**
 * Thrown to indicate that a command cannot be executed
 */
public class CommandException extends RuntimeException {
    /**
     * Constructs a new duke parser exception with the specified detail message.
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public CommandException(String message) {
        super(message);
    }
}


