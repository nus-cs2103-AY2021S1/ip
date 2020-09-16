package duke.exception;

public class InvalidDoneCommandException extends DukeException {
    public InvalidDoneCommandException() {
        super("This task is already done!\n"
                + "Check out each task's status by using 'list'!");
    }
}
