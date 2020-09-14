/**
 * Thrown to indicate that a command to delete tasks is invalid. The command is invalid
 * if the tasks to be deleted are not specified, or a task to be deleted is not specified
 * by a number, or the number specified does not represent an existing task.
 */

public class InvalidDeleteException extends Exception {

    /**
     * Constructs a InvalidDeleteException with the specified detail message.
     * @param message Detail message.
     */
    public InvalidDeleteException(String message) {
        super(message);
    }

}
