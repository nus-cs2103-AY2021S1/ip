package duke.exception;

public class DeletionEmptyException extends  DukeException{
    public DeletionEmptyException() {
        super("☹ OOPS!!! The deletion index cannot be empty.");
    }
}
