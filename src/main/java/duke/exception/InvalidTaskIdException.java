package duke.exception;

public class InvalidTaskIdException extends DukeException {
    public InvalidTaskIdException(String message) {
        super(message + "Please key in only the integer representing the task!");
    }
}
