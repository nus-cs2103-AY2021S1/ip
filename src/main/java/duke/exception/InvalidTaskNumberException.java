package duke.exception;

/**
 * Thrown when the task number provided by the user is not valid.
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Initializes the InvalidTaskNumberException object with the error message suggesting the proper format.
     *
     * @param size Size provided by the user.
     */
    public InvalidTaskNumberException(int size) {
        super(String.format("Task number does not exist in the list.\n    "
                + "Your current list only has %d tasks!", size));
    }
}