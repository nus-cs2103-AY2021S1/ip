package duke.exception;

public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("☹ OOPS!!! The description or date of a deadline cannot be empty.");
    }
}
