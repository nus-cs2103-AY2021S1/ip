package duke.exception;

public class DeleteException extends DukeException {
    public DeleteException() {
        super("OOPS!!! You need a task number to use delete!");
    }
}
