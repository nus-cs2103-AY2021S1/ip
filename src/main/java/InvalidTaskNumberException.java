/**
 * Class to handle the case where an invalid task number is entered
 * @author vanGoghhh
 */

public class InvalidTaskNumberException extends DukeException {

    public InvalidTaskNumberException() {
        super("The task number is invalid or empty!");
    }


}
