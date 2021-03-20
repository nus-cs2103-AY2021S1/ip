/**
 * Thrown to indicate that a command to add tasks is invalid. The command is invalid
 * if a ToDo is lacking a description, or a Deadline is lacking a description or date,
 * or an Event is lacking a description or date.
 */
public class InvalidTaskArgumentException extends Exception {

    /**
     * Constructs an InvalidTaskArgumentException with the specified detail message.
     * @param message Detail message.
     */
    public InvalidTaskArgumentException(String message) {
        super(message);
    }

}
