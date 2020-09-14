package duke.exception;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Your list is empty.");
    }
}
