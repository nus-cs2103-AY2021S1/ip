/**
 * Class to handle the case where an invalid task description is entered
 * @author vanGoghhh
 */

public class InvalidTaskDescriptionException extends DukeException {

    public InvalidTaskDescriptionException() {
        super("The task description is invalid or empty!");
    }

}
