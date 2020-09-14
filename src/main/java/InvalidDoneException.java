/**
 * Thrown to indicate that a command to mark tasks as done is invalid. The command is invalid
 * if the tasks to be marked as done are not specified, or a task to be marked as done is not
 * specified by a number, or the number specified does not represent an existing task.
 */
public class InvalidDoneException extends Exception {

    /**
     * Constructs a InvalidDoneException with the specified detail message.
     * @param message Detail message.
     */
    public InvalidDoneException(String message) {
        super(message);
    }

}
