package duke.exception;

public class InvalidTaskNumberException extends DukeException{
    public InvalidTaskNumberException() {
        super("OOPS!!! Task Number is invalid.");
    }
}
