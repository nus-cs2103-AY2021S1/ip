package duke.exception;

public class DeleteIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify which task to be deleted.";
    }
}
