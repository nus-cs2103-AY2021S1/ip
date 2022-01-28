package duke.exception;

/**
 * Thrown when the task Id entered by the user does not exist in the list of tasks.
 */
public class InvalidTaskIdException extends DukeException {

    public InvalidTaskIdException(String id) {
        super("404 task with id " + id + " not found. Please enter a correct task id");
    }

    public String toString() {
        return getMessage();
    }
}
