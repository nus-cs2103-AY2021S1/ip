package duke.exception;

public class InvalidDoneException extends DukeException {
    public InvalidDoneException() {
        super("Done format is invalid.\n    Please try again with a proper format like 'done 3'");
    }
}