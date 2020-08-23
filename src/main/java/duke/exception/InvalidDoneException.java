package duke.exception;

public class InvalidDoneException extends DukeException{

    public InvalidDoneException() {
        super("OOPS!!! The task to be done has to be a number.");
    }
}
