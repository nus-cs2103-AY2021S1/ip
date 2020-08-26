package duke.exception;

public class DoneIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify which task to be marked as done.";
    }
}
