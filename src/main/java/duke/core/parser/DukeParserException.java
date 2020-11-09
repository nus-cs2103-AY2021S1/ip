package duke.core.parser;

/*
Javadoc comments copied/adapted from
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/RuntimeException.html
 */

/**
 * Thrown to indicate that a method has been passed an inappropriate argument.
 */
public class DukeParserException extends RuntimeException {

    /**
     * Constructs a new duke parser exception with the specified detail message.
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public DukeParserException(String message) {
        super(message);
    }

}
