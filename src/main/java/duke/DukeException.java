package duke;

/**
 * Thrown to indicate that a method call has resulted in an error and command is unable to complete.
 */
public class DukeException extends Exception{

    /**
     * Constructs an <code>DukeException</code> with the specified detail message.
     *
     * @param message the detail message
     */
    public DukeException(String message) {
        super(message);
    }
}
